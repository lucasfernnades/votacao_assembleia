package com.sicredi.votacao_assembleia.dto;

public class PautaRequestDTO {

    private String nome;

    private String descricao;

    public PautaRequestDTO() {
    }

    public PautaRequestDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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
}
