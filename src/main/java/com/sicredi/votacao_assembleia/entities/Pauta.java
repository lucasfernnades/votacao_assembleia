package com.sicredi.votacao_assembleia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
}