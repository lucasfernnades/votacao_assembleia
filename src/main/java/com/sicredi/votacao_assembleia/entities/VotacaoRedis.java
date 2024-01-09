package com.sicredi.votacao_assembleia.entities;

import com.sicredi.votacao_assembleia.dto.PautaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

    public VotacaoRedis(Votacao votacao) {
        this.id = votacao.getId().toString();
        this.pautaDto = new PautaResponseDTO(votacao.getPauta());
        this.tempoParaExpirar = votacao.getTempoParaExpirar();
        this.dataExpiracao = votacao.getDataExpiracao();
        this.votos = votacao.getVotos();
        this.closed = votacao.isClosed();
    }
}
