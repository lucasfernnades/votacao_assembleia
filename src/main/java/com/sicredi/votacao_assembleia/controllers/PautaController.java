package com.sicredi.votacao_assembleia.controllers;

import com.sicredi.votacao_assembleia.dto.PautaRequestDTO;
import com.sicredi.votacao_assembleia.dto.PautaResponseDTO;
import com.sicredi.votacao_assembleia.services.PautaRedisService;
import com.sicredi.votacao_assembleia.services.PautaService;
import com.sicredi.votacao_assembleia.services.RabbitMqService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pauta")
public class PautaController {

    @Autowired
    private PautaService service;

    @Autowired
    private PautaRedisService redisService;

    @ApiOperation(value="Retorna todas as pautas", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta encontrada.")
    })
    @GetMapping()
    public ResponseEntity<?> getTodasPautas() {
        return ResponseEntity.ok(redisService.findAll());
    }

    @ApiOperation(value="Retorna uma pauta", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPauta(@PathVariable String id) {
        return ResponseEntity.ok(redisService.getById(id));
    }

    @ApiOperation(value="Cria uma nova pauta", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta criada com sucesso.")
    })
    @PostMapping()
    public ResponseEntity<?> criarNovaPauta(@RequestBody PautaRequestDTO pauta) {
        PautaResponseDTO response = service.criarNovaPauta(pauta);
        return ResponseEntity.ok().body(response);
    }
}
