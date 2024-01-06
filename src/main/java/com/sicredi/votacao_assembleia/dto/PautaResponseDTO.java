package com.sicredi.votacao_assembleia.dto;

import com.sicredi.votacao_assembleia.entities.Pauta;

import java.io.Serializable;

public class PautaResponseDTO implements Serializable {

    private String id;

    private String name;

    private String descricao;

    public PautaResponseDTO() {
    }

    public PautaResponseDTO(String id, String name, String descricao) {
        this.id = id;
        this.name = name;
        this.descricao = descricao;
    }

    public PautaResponseDTO(Pauta pauta) {
        this.id = pauta.getId().toString();
        this.name = pauta.getNome();
        this.descricao = pauta.getDescricao();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "PautaResponseDTO {\n" +
                "      id= " + id + "\n" +
                "      name= " + name + "\n" +
                "      descricao= " + descricao + "\n" +
                "   }";
    }
}
