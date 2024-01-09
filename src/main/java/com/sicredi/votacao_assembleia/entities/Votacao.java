package com.sicredi.votacao_assembleia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "votacao")
public class Votacao {

    @Id
    private ObjectId id;

    private Pauta pauta;

    private Integer tempoParaExpirar;

    private Instant dataExpiracao;

    private List<Voto> votos;

    private boolean closed;

    public Votacao(Pauta pauta, Integer tempoParaExpirar) {
        this.pauta = pauta;
        this.tempoParaExpirar = tempoParaExpirar;
        this.dataExpiracao = Instant.now().plusSeconds(tempoParaExpirar * 60L);
        this.votos = new ArrayList<>();
    }

    public void addVoto(Voto voto) {
        votos.add(voto);
    }

    public boolean isExpirado(){
        return this.getDataExpiracao().isBefore(Instant.now());
    }
}
