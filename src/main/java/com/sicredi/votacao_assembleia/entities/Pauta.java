package com.sicredi.votacao_assembleia.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "pauta")
public class Pauta {

    @Id
    private ObjectId id;

    private String nome;

    private String descricao;

    public Pauta(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
}
