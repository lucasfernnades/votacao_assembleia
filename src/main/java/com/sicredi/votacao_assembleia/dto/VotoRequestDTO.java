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
public class VotoRequestDTO {

    private final String errorValid = "Campo não pode ser nulo nem vazio";

    @NotNull(message = errorValid)
    private String votacaoId;

    @NotNull(message = errorValid)
    private String resposta;

    @NotNull(message = errorValid)
    private String cpf;

    public boolean formatoRespostaValido(String resposta) {
        String[] possiveisRespostas = {"sim", "não", "nao"};

        for (String r: possiveisRespostas) {
            if(r.equals(resposta))
                return true;
        }
        return false;
    }
}
