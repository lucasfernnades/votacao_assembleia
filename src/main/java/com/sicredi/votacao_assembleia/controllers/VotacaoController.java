package com.sicredi.votacao_assembleia.controllers;

import com.sicredi.votacao_assembleia.dto.VotacaoRequestDTO;
import com.sicredi.votacao_assembleia.dto.VotacaoResponseDTO;
import com.sicredi.votacao_assembleia.dto.VotoRequestDTO;
import com.sicredi.votacao_assembleia.dto.VotoResponseDTO;
import com.sicredi.votacao_assembleia.services.VotacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "votacao")
public class VotacaoController {

    @Autowired
    private VotacaoService service;

    @ApiOperation(value="Retorna todas as votações", response = VotacaoResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Votações encontradas.")
    })
    @GetMapping()
    public ResponseEntity<?> getTodasVotacoes(){
        return ResponseEntity.ok(service.listarTodasVotacoes());
    }

    @ApiOperation(value="Retorna uma votação", response = VotacaoResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Votação encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getVotacao(@PathVariable String id){
        return ResponseEntity.ok(service.getVotacao(id));
    }

    @ApiOperation(value="Cria uma nova votação", response = VotacaoResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Votação criada com sucesso.")
    })
    @PostMapping()
    public ResponseEntity<?> criarNovaVotacao(@RequestBody VotacaoRequestDTO votacao) {
        VotacaoResponseDTO response = service.criarNovaVotacao(votacao);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value="Adiciona um voto na votação desejada", response = VotacaoResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Voto adicionado com sucesso.")
    })
    @PutMapping("/voto")
    public ResponseEntity<?> voto(@RequestBody VotoRequestDTO voto) {
        VotoResponseDTO response = service.adicionarVoto(voto);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value="Retorna o resultado da votação", response = VotacaoResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resultado da votação encontrado.")
    })
    @GetMapping("/resultado/{id}")
    public ResponseEntity<?> getResultadoVotacao(@PathVariable String id){
        return ResponseEntity.ok(service.getResultadoVotacao(id));
    }
}