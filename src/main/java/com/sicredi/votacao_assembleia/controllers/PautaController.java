package com.sicredi.votacao_assembleia.controllers;

import com.sicredi.votacao_assembleia.dto.PautaRequestDTO;
import com.sicredi.votacao_assembleia.dto.PautaResponseDTO;
import com.sicredi.votacao_assembleia.services.PautaRedisService;
import com.sicredi.votacao_assembleia.services.PautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping(value = "v1.0/pauta")
public class PautaController {

    private final PautaService service;

    private final PautaRedisService redisService;

    @Autowired
    public PautaController(PautaService service, PautaRedisService redisService) {
        this.service = service;
        this.redisService = redisService;
    }

    @ApiOperation(value="Retorna todas as pautas", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta encontrada.")
    })
    @GetMapping()
    public ResponseEntity<List<PautaResponseDTO>> getTodasPautas() {
        return ResponseEntity.ok(redisService.findAll());
    }

    @ApiOperation(value="Retorna uma pauta", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PautaResponseDTO> getPauta(@PathVariable @NotNull String id) {
        return ResponseEntity.ok(redisService.getById(id));
    }

    @ApiOperation(value="Cria uma nova pauta", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta criada com sucesso.")
    })
    @PostMapping()
    public ResponseEntity<PautaResponseDTO> criarNovaPauta(@RequestBody @Valid PautaRequestDTO pauta) {
        PautaResponseDTO response = service.criarNovaPauta(pauta);
        return ResponseEntity.ok().body(response);
    }
}
