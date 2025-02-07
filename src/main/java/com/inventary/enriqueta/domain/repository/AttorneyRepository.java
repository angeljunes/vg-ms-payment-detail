package com.inventary.enriqueta.domain.repository;

import com.inventary.enriqueta.domain.model.Attorney;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AttorneyRepository extends ReactiveMongoRepository<Attorney, String> {


}
