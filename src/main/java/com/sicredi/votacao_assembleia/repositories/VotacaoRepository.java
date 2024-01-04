package com.sicredi.votacao_assembleia.repositories;

import com.sicredi.votacao_assembleia.entities.Votacao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepository extends MongoRepository<Votacao, ObjectId> {
}
