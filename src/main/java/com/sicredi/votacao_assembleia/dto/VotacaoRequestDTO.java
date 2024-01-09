package com.sicredi.votacao_assembleia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VotacaoRequestDTO {

    @NotNull(message = "Campo não pode ser nulo nem vazio")
    private String pautaId;

    private Integer tempoParaExpirar;
}
