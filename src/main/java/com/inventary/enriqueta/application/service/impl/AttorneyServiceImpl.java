package com.inventary.enriqueta.application.service.impl;

import com.inventary.enriqueta.application.service.AttorneyService;
import com.inventary.enriqueta.domain.model.Attorney;
import com.inventary.enriqueta.domain.repository.AttorneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class AttorneyServiceImpl implements AttorneyService {



    @Autowired
    private AttorneyRepository attorneyRepository;

    @Override
    public Flux<Attorney> listAllActive() {
        return attorneyRepository.findAll();
    }

    @Override
    public Mono<Attorney> findById(String id) {
        return attorneyRepository.findById(id);
    }

    @Override
    public Mono<Attorney> createAttorney(Attorney attorney) {
        return attorneyRepository.save(attorney);
    }

    @Override
    public Mono<Attorney> deleteAttorney(String id) {
        attorneyRepository.deleteById(id);
        return Mono.empty();
    }

}
