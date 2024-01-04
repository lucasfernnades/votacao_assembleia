package com.sicredi.votacao_assembleia.repositories;

import com.sicredi.votacao_assembleia.entities.Pauta;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, ObjectId> {
}
