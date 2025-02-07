package com.inventary.enriqueta.domain.repository;

import com.inventary.enriqueta.domain.model.PaymentDetail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PaymentDetailRepository extends ReactiveMongoRepository<PaymentDetail, String> {

    Flux<PaymentDetail> findByStatus(String status);
}
