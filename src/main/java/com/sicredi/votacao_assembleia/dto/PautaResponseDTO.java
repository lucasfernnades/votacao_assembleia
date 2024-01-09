package com.sicredi.votacao_assembleia.dto;

import com.sicredi.votacao_assembleia.entities.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PautaResponseDTO implements Serializable {

    private String id;

    private String nome;

    private String descricao;

    public PautaResponseDTO(Pauta pauta) {
        this.id = pauta.getId().toString();
        this.nome = pauta.getNome();
        this.descricao = pauta.getDescricao();
    }

    @Override
    public String toString() {
        return "PautaResponseDTO {\n" +
                "      id= " + id + "\n" +
                "      nome= " + nome + "\n" +
                "      descricao= " + descricao + "\n" +
                "   }";
    }
}
