package com.sicredi.votacao_assembleia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResultadoVotacaoResponseDTO implements Serializable {

    private PautaResponseDTO pauta;

    private Long qtdSim;

    private Long qtdNao;

    @Override
    public String toString() {
        return "\nResultadoVotacaoResponseDTO {\n" +
                "   pauta= " + pauta + "\n" +
                "   qtdSim= " + qtdSim + "\n" +
                "   qtdNao= " + qtdNao + "\n" +
                "}";
    }
}
