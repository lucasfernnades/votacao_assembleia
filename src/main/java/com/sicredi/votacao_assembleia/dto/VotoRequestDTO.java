package com.sicredi.votacao_assembleia.dto;

public class VotoRequestDTO {

    private String votacaoId;

    private String resposta;

    private String cpf;

    public String getVotacaoId() {
        return votacaoId;
    }

    public void setVotacaoId(String votacaoId) {
        this.votacaoId = votacaoId;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean formatoRespostaValido(String resposta) {
        String[] possiveisRespostas = {"sim", "não", "nao"};

        for (String r: possiveisRespostas) {
            if(r.equals(resposta))
                return true;
        }
        return false;
    }
}
