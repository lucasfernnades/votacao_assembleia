package com.sicredi.votacao_assembleia.services;

import com.sicredi.votacao_assembleia.dto.VotacaoResponseDTO;
import com.sicredi.votacao_assembleia.entities.Votacao;
import com.sicredi.votacao_assembleia.entities.VotacaoRedis;
import com.sicredi.votacao_assembleia.exception.BusinessException;
import com.sicredi.votacao_assembleia.repositories.VotacaoRedisRepository;
import com.sicredi.votacao_assembleia.repositories.VotacaoRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class VotacaoRedisService {

    private final VotacaoRedisRepository redisRepository;

    private final VotacaoRepository mongoRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public VotacaoRedisService(VotacaoRedisRepository redisRepository,
                               VotacaoRepository mongoRepository, ModelMapper modelMapper) {
        this.redisRepository = redisRepository;
        this.mongoRepository = mongoRepository;
        this.modelMapper = modelMapper;
    }

    public List<VotacaoResponseDTO> findAll() {
        List<VotacaoRedis> votacoesRedis = (List<VotacaoRedis>) redisRepository.findAll();

        List<VotacaoResponseDTO> votacaoResponseDto = new ArrayList<>();

        if (CollectionUtils.isEmpty(votacoesRedis)) {
            List<Votacao> votacoes = mongoRepository.findAll();

            votacoes.forEach(
                    votacao -> votacaoResponseDto.add(new VotacaoResponseDTO(votacao)));
            votacoes.forEach(
                    votacao -> votacoesRedis.add(new VotacaoRedis(votacao))
            );

            redisRepository.saveAll(votacoesRedis);
        } else {
            votacoesRedis.forEach(
                    votacaoRedis -> votacaoResponseDto.add(modelMapper.map(votacaoRedis, VotacaoResponseDTO.class)));
        }

        return votacaoResponseDto;
    }

    public VotacaoResponseDTO getById(String id) {
        Votacao votacao;

        if (redisRepository.findById(id).isEmpty()) {
            votacao = mongoRepository.findById(new ObjectId()).orElseThrow(
                    () -> new BusinessException("Nenhuma votação encontrada no MongoDb!"));
            return new VotacaoResponseDTO(votacao);
        }

        VotacaoRedis votacaoRedis = redisRepository.findById(id).orElseThrow(
                () -> new BusinessException("Nenhuma votação encontrada no Redis!"));

        return modelMapper.map(votacaoRedis, VotacaoResponseDTO.class);
    }

    public void adicionaNovaVotacaoNoCache(VotacaoResponseDTO dto) {
        List<VotacaoResponseDTO> votacaoCache = findAll();
        votacaoCache.add(dto);

        List<VotacaoRedis> votacaoRedis = new ArrayList<>();

        votacaoCache.forEach(votacao -> votacaoRedis.add(modelMapper.map(votacao, VotacaoRedis.class)));

        redisRepository.saveAll(votacaoRedis);
    }

    public void saveVotoCache(VotacaoResponseDTO dto) {
        redisRepository.delete(modelMapper.map(getById(dto.getId()), VotacaoRedis.class));

        redisRepository.save(modelMapper.map(dto, VotacaoRedis.class));
    }
}
