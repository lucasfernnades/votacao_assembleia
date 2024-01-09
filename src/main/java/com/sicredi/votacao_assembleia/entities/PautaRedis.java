package com.sicredi.votacao_assembleia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@RedisHash("pauta")
public class PautaRedis {

    @Id
    @Indexed
    private String id;

    private String nome;

    private String descricao;
}