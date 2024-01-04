package com.sicredi.votacao_assembleia.dto;

public class ResultadoVotacaoResponseDTO {

    private PautaResponseDTO pauta;

    private Long qtdSim;

    private Long qtdNao;

    public ResultadoVotacaoResponseDTO(PautaResponseDTO pauta, Long sim, Long nao) {
        this.pauta = pauta;
        this.qtdSim = sim;
        this.qtdNao = nao;
    }

    public PautaResponseDTO getPauta() {
        return pauta;
    }

    public void setPauta(PautaResponseDTO pauta) {
        this.pauta = pauta;
    }

    public Long getQtdSim() {
        return qtdSim;
    }

    public void setQtdSim(Long qtdSim) {
        this.qtdSim = qtdSim;
    }

    public Long getQtdNao() {
        return qtdNao;
    }

    public void setQtdNao(Long qtdNao) {
        this.qtdNao = qtdNao;
    }
}
