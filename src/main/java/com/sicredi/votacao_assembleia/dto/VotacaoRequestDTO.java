package com.sicredi.votacao_assembleia.dto;

public class VotacaoRequestDTO {

    private String pautaId;

    private Integer tempoParaExpirar;

    public String getPautaId() {
        return pautaId;
    }

    public void setPautaId(String pautaId) {
        this.pautaId = pautaId;
    }

    public Integer getTempoParaExpirar() {
        return tempoParaExpirar;
    }

    public void setTempoParaExpirar(Integer tempoParaExpirar) {
        this.tempoParaExpirar = tempoParaExpirar;
    }
}
