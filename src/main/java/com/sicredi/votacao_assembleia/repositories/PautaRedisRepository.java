package com.sicredi.votacao_assembleia.repositories;

import com.sicredi.votacao_assembleia.entities.PautaRedis;
import org.springframework.data.repository.CrudRepository;

public interface PautaRedisRepository extends CrudRepository<PautaRedis, String> {
}
