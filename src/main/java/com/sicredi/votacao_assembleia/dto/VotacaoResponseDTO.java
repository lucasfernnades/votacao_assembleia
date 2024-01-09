package com.sicredi.votacao_assembleia.dto;

import com.sicredi.votacao_assembleia.entities.Votacao;
import com.sicredi.votacao_assembleia.entities.Voto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
}
