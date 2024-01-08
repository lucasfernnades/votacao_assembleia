package com.sicredi.votacao_assembleia.entities;

import com.sicredi.votacao_assembleia.dto.PautaResponseDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;
import java.util.List;

@RedisHash("votacao")
public class VotacaoRedis {

    @Id
    @Indexed
    private String id;

    private PautaResponseDTO pautaDto;

    private Integer tempoParaExpirar;

    private Instant dataExpiracao;

    private List<Voto> votos;

    private boolean closed;

    public VotacaoRedis() {
    }

    public VotacaoRedis(String id, PautaResponseDTO pautaDto, Integer tempoParaExpirar,
                        Instant dataExpiracao, List<Voto> votos, boolean closed) {
        this.id = id;
        this.pautaDto = pautaDto;
        this.tempoParaExpirar = tempoParaExpirar;
        this.dataExpiracao = dataExpiracao;
        this.votos = votos;
        this.closed = closed;
    }

    public VotacaoRedis(Votacao votacao) {
        this.id = votacao.getId().toString();
        this.pautaDto = new PautaResponseDTO(votacao.getPauta());
        this.tempoParaExpirar = votacao.getTempoParaExpirar();
        this.dataExpiracao = votacao.getDataExpiracao();
        this.votos = votacao.getVotos();
        this.closed = votacao.isClosed();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PautaResponseDTO getPautaDto() {
        return pautaDto;
    }

    public void setPautaDto(PautaResponseDTO pautaDto) {
        this.pautaDto = pautaDto;
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
