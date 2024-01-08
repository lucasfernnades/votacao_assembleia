package com.sicredi.votacao_assembleia.repositories;

import com.sicredi.votacao_assembleia.entities.VotacaoRedis;
import org.springframework.data.repository.CrudRepository;

public interface VotacaoRedisRepository extends CrudRepository<VotacaoRedis, String> {
}
