package com.sicredi.votacao_assembleia.services;

import com.sicredi.votacao_assembleia.constants.RabbitMqConstants;
import com.sicredi.votacao_assembleia.dto.*;
import com.sicredi.votacao_assembleia.entities.Pauta;
import com.sicredi.votacao_assembleia.entities.Votacao;
import com.sicredi.votacao_assembleia.entities.Voto;
import com.sicredi.votacao_assembleia.exception.BusinessException;
import com.sicredi.votacao_assembleia.repositories.PautaRepository;
import com.sicredi.votacao_assembleia.repositories.VotacaoRepository;
import org.bson.types.ObjectId;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotacaoService {

    private final PautaRepository pautaRepository;

    private final VotacaoRepository votacaoRepository;

    private final ValidadorCpfService validadorCpfService;

    private final RabbitMqService rabbitMqService;

    private final VotacaoRedisService votacaoRedisService;

    public VotacaoService(PautaRepository pautaRepository, VotacaoRepository votacaoRepository,
                          ValidadorCpfService validadorCpfService, RabbitMqService rabbitMqService,
                          VotacaoRedisService votacaoRedisService) {
        this.pautaRepository = pautaRepository;
        this.votacaoRepository = votacaoRepository;
        this.validadorCpfService = validadorCpfService;
        this.rabbitMqService = rabbitMqService;
        this.votacaoRedisService = votacaoRedisService;
    }

    public ResultadoVotacaoResponseDTO getResultadoVotacao(String id) {
        Votacao votacao = votacaoRepository.
                    findById(new ObjectId(id)).orElseThrow(() -> new BusinessException("Nenhuma votação encontrada!"));

        if (!votacao.isClosed())
            throw new BusinessException(
                    "A votação atual não encontra-se fechada, aguarde o fechamento para conferir o resultado.");

        List<Voto> votos = votacao.getVotos();

        return new ResultadoVotacaoResponseDTO(new PautaResponseDTO(votacao.getPauta()),
                votos.stream().filter(v -> v.getResposta().equals("sim")).count(),
                votos.stream().filter(v -> v.getResposta().equals("não")).count());
    }

    public VotacaoResponseDTO criarNovaVotacao(VotacaoRequestDTO dto) {
        Pauta pauta = pautaRepository.findById(new ObjectId(dto.getPautaId())).orElseThrow(
                () -> new BusinessException("Nenhuma pauta encontrada!"));

        Integer tempoParaExpirar = dto.getTempoParaExpirar();
        if (tempoParaExpirar == null || tempoParaExpirar <= 0) {
            tempoParaExpirar = 1;
        }

        Votacao votacao = new Votacao(pauta, tempoParaExpirar);

        VotacaoResponseDTO votacaoResponseDTO = new VotacaoResponseDTO(votacaoRepository.save(votacao));

        votacaoRedisService.adicionaNovaVotacaoNoCache(votacaoResponseDTO);

        return votacaoResponseDTO;
    }

    public VotoResponseDTO adicionarVoto(VotoRequestDTO dto) {
        Votacao votacao = votacaoRepository.findById(
                new ObjectId(dto.getVotacaoId())).orElseThrow(
                        () -> new BusinessException("Nenhuma votação encontrada!"));

        validarVoto(votacao, dto);

        if(dto.getResposta().equals("nao"))
            dto.setResposta("não");

        Voto voto = new Voto(dto.getCpf(), dto.getResposta());
        votacao.addVoto(voto);

        VotacaoResponseDTO votacaoResponseDTO = new VotacaoResponseDTO(votacaoRepository.save(votacao));
        votacaoRedisService.saveVotoCache(votacaoResponseDTO);

        return new VotoResponseDTO(true);
    }

    public void validarVoto(Votacao votacao, VotoRequestDTO dto) {
        if (!dto.formatoRespostaValido(dto.getResposta()))
            throw new BusinessException("Apenas são aceitos votos de sim ou não, favor verificar sua resposta.");

        if (votacao.getVotos().stream().anyMatch(v -> v.getCpf().equals(dto.getCpf())))
            throw new BusinessException("Já há um voto registrado com o CPF digitado, favor verificar e tentar novamente.");

        if (votacao.isExpirado() || votacao.isClosed())
            throw new BusinessException("Votação já se encontrada expirada/fechada.");

        try {
            if (!validadorCpfService.isCpfvalido(dto.getCpf()))
                throw new BusinessException("CPF inválido, favor digite um CPF válido para votar");
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("O serviço de validação de CPF foi interrompido ou está indisponivel.");
        }
    }

    @Scheduled(fixedDelay = 1000)
    private void fecharESalvarResultadoVotacoesExpiradas() {
        List<Votacao> votacoes = votacaoRepository.
                findAll().stream().filter(v -> v.isExpirado() && !v.isClosed()).collect(Collectors.toList());

        votacoes.forEach(v -> {
            v.setClosed(true);
            votacaoRepository.save(v);

            ResultadoVotacaoResponseDTO resultadoVotacaoDto = getResultadoVotacao(v.getId().toString());
            rabbitMqService.sendMessage(RabbitMqConstants.FILA_RESULTADO_VOTACAO, resultadoVotacaoDto);
        });
    }
}
