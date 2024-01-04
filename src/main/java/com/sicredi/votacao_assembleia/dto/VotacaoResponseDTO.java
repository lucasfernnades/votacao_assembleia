package com.sicredi.votacao_assembleia.dto;

import com.sicredi.votacao_assembleia.entities.Votacao;
import com.sicredi.votacao_assembleia.entities.Voto;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.List;

public class VotacaoResponseDTO {

    private String id;

    private PautaResponseDTO pautaDto;

    private Integer tempoParaExpirar;

    private Instant dataExpiracao;

    private List<Voto> votos;

    private boolean closed;

    public VotacaoResponseDTO(Votacao votacao) {
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
