package com.sicredi.votacao_assembleia.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "votacao")
public class Votacao {

    @Id
    private ObjectId id;

    private Pauta pauta;

    private Integer tempoParaExpirar;

    private Instant dataExpiracao;

    private List<Voto> votos;

    private boolean closed;

    public Votacao() {
    }

    public Votacao(Pauta pauta, Integer tempoParaExpirar) {
        this.pauta = pauta;
        this.tempoParaExpirar = tempoParaExpirar;
        this.dataExpiracao = Instant.now().plusSeconds(tempoParaExpirar * 60);
        this.votos = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Integer getTempoParaExpirar() {
        return tempoParaExpirar;
    }

    public void setTempoParaExpirar(Integer tempoParaExpirar) {
        this.tempoParaExpirar = tempoParaExpirar;
    }

    public Instant getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Instant dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public void addVoto(Voto voto) {
        votos.add(voto);
    }

    public boolean isExpirado(){
        return this.getDataExpiracao().isBefore(Instant.now());
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
