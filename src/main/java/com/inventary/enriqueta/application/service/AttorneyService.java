package com.inventary.enriqueta.application.service;

import com.inventary.enriqueta.domain.model.Attorney;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AttorneyService {
    Flux<Attorney> listAllActive();
    Mono<Attorney> createAttorney (Attorney attorney);
    Mono<Attorney> deleteAttorney(String id);
    Mono<Attorney> findById(String id);

}
