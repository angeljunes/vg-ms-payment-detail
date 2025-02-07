package com.inventary.enriqueta.application.service;

import com.inventary.enriqueta.domain.model.PaymentDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PaymentDetailService {
    Flux<PaymentDetail> listAll();
    Mono<PaymentDetail> findById(String id);

    Flux<PaymentDetail> findByStatus(String status);

    Mono<PaymentDetail> createPaymentDetail(PaymentDetail paymentDetail);
    Mono<PaymentDetail> updatePaymentDetail(String id, PaymentDetail paymentDetail);
    Mono<PaymentDetail> deletePaymentDetail(String id);
    Mono<PaymentDetail> reactivatePaymentDetail(String id);

    // Nuevos métodos para listar activos e inactivos
    Flux<PaymentDetail> listAllActive();
    Flux<PaymentDetail> listAllInactive();

    Mono<Boolean> validateTokenAndRoles(String token, List<String> requiredRoles);
}
