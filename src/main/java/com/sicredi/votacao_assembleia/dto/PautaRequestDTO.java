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
public class PautaRequestDTO {

    private final String errorValid = "Campo não pode ser nulo nem vazio";

    @NotNull(message = errorValid)
    private String nome;

    @NotNull(message = errorValid)
    private String descricao;
}
