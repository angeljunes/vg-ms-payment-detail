package com.inventary.enriqueta.application.service.impl;

import com.inventary.enriqueta.application.service.PaymentService;
import com.inventary.enriqueta.domain.model.Payment;
import com.inventary.enriqueta.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PaymentServiceImpl implements PaymentService {



    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Flux<Payment> listAllActive() {
        return paymentRepository.findAll();
    }

    @Override
    public Mono<Payment> findById(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Mono<Payment> createPayment(Payment Payment) {
        return paymentRepository.save(Payment);
    }

    @Override
    public Mono<Payment> deletePayment(String id) {
        paymentRepository.deleteById(id);
        return Mono.empty();
    }

}
