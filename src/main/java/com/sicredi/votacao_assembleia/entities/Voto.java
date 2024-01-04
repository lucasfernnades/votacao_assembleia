package com.sicredi.votacao_assembleia.entities;

public class Voto {
    private String cpf;
    private String resposta;

    public Voto(String cpf, String resposta) {
        this.cpf = cpf;
        this.resposta = resposta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
