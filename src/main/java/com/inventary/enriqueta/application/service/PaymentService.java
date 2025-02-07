package com.inventary.enriqueta.application.service;

import com.inventary.enriqueta.domain.model.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Flux<Payment> listAllActive();
    Mono<Payment> createPayment(Payment payment);
    Mono<Payment> deletePayment(String id);
    Mono<Payment> findById(String id);
}
