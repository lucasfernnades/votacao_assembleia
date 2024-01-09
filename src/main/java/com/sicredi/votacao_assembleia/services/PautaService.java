package com.sicredi.votacao_assembleia.services;

import com.sicredi.votacao_assembleia.dto.PautaRequestDTO;
import com.sicredi.votacao_assembleia.dto.PautaResponseDTO;
import com.sicredi.votacao_assembleia.entities.Pauta;
import com.sicredi.votacao_assembleia.repositories.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private final PautaRepository repository;

    private final PautaRedisService redisService;

    @Autowired
    public PautaService(PautaRepository repository, PautaRedisService redisService) {
        this.repository = repository;
        this.redisService = redisService;
    }

    public PautaResponseDTO criarNovaPauta(PautaRequestDTO dto) {
        PautaResponseDTO pautaResponseDTO = new PautaResponseDTO(
                repository.save(new Pauta(dto.getNome(), dto.getDescricao())));

        redisService.adicionaNovaPautaNaListaCache(pautaResponseDTO);
        return pautaResponseDTO;
    }
}
