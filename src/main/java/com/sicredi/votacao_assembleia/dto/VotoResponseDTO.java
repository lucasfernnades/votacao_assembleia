package com.sicredi.votacao_assembleia.dto;

public class VotoResponseDTO {

    private boolean votoSucesso;

    public VotoResponseDTO(boolean votoSucesso) {
        this.votoSucesso = votoSucesso;
    }

    public boolean isVotoSucesso() {
        return votoSucesso;
    }

    public void setVotoSucesso(boolean votoSucesso) {
        this.votoSucesso = votoSucesso;
    }
}
