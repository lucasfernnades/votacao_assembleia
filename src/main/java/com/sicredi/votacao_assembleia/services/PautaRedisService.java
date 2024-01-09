package com.sicredi.votacao_assembleia.services;

import com.sicredi.votacao_assembleia.dto.PautaResponseDTO;
import com.sicredi.votacao_assembleia.entities.Pauta;
import com.sicredi.votacao_assembleia.entities.PautaRedis;
import com.sicredi.votacao_assembleia.exception.BusinessException;
import com.sicredi.votacao_assembleia.repositories.PautaRedisRepository;
import com.sicredi.votacao_assembleia.repositories.PautaRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PautaRedisService {

    private final PautaRedisRepository redisRepository;

    private final PautaRepository mongoRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PautaRedisService(PautaRedisRepository redisRepository,
                             PautaRepository mongoRepository, ModelMapper modelMapper) {
        this.redisRepository = redisRepository;
        this.mongoRepository = mongoRepository;
        this.modelMapper = modelMapper;
    }

    public List<PautaResponseDTO> findAll() {
        List<PautaRedis> pautasRedis = (List<PautaRedis>) redisRepository.findAll();

        List<PautaResponseDTO> pautaResponseDto = new ArrayList<>();

        if (CollectionUtils.isEmpty(pautasRedis)) {
            List<Pauta> pautas = mongoRepository.findAll();

            pautas.forEach(
                    pauta -> pautaResponseDto.add(modelMapper.map(pauta, PautaResponseDTO.class)));
            pautaResponseDto.forEach(
                    pauta -> pautasRedis.add(modelMapper.map(pauta, PautaRedis.class)));

            redisRepository.saveAll(pautasRedis);
        } else {
            pautasRedis.forEach(pautaRedis -> pautaResponseDto.add(modelMapper.map(pautaRedis, PautaResponseDTO.class)));
        }

        return pautaResponseDto;
    }

    public PautaResponseDTO getById(String id) {
        Pauta pauta;

        if (redisRepository.findById(id).isEmpty()) {
            pauta = mongoRepository.findById(new ObjectId(id)).orElseThrow(
                    () -> new BusinessException("Nenhuma pauta encontrada no MongoDb!"));
            return new PautaResponseDTO(pauta);
        }

        PautaRedis pautaRedis = redisRepository.findById(id).orElseThrow(
                () -> new BusinessException("Nenhuma pauta encontrada no Redis!"));

        return modelMapper.map(pautaRedis, PautaResponseDTO.class);
    }

    public void adicionaNovaPautaNaListaCache(PautaResponseDTO dto) {
        List<PautaResponseDTO> pautasCache = findAll();
        pautasCache.add(dto);

        List<PautaRedis> pautaRedis = new ArrayList<>();

        pautasCache.forEach(pauta -> pautaRedis.add(modelMapper.map(pauta, PautaRedis.class)));

        redisRepository.saveAll(pautaRedis);
    }
}
