package com.sicredi.votacao_assembleia.dto;

import com.sicredi.votacao_assembleia.entities.Pauta;

import java.io.Serializable;

public class PautaResponseDTO implements Serializable {

    private String id;

    private String nome;

    private String descricao;

    public PautaResponseDTO() {
    }

    public PautaResponseDTO(String id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public PautaResponseDTO(Pauta pauta) {
        this.id = pauta.getId().toString();
        this.nome = pauta.getNome();
        this.descricao = pauta.getDescricao();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
                "      nome= " + nome + "\n" +
                "      descricao= " + descricao + "\n" +
                "   }";
    }
}
