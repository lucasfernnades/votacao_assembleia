package com.sicredi.votacao_assembleia.services;

import com.sicredi.votacao_assembleia.dto.PautaRequestDTO;
import com.sicredi.votacao_assembleia.dto.PautaResponseDTO;
import com.sicredi.votacao_assembleia.entities.Pauta;
import com.sicredi.votacao_assembleia.repositories.PautaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;

    public List<PautaResponseDTO> listarTodasPautas() {
        return repository.findAll().stream().map(PautaResponseDTO::new).collect(Collectors.toList());
    }

    public PautaResponseDTO getPauta(String id) {
        return new PautaResponseDTO(repository.
                findById(new ObjectId(id)).orElseThrow(NullPointerException::new));
    }

    public PautaResponseDTO criarNovaPauta(PautaRequestDTO dto) {
        return new PautaResponseDTO(repository.save(new Pauta(dto.getNome(), dto.getDescricao())));
    }
}
