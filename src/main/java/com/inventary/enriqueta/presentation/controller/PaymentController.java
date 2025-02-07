package com.inventary.enriqueta.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.inventary.enriqueta.domain.model.Payment;
import com.inventary.enriqueta.application.service.PaymentService;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService PaymentService) {
        this.paymentService = PaymentService;
    }

    @GetMapping
    public Flux<Payment> getAllPayment() {
        return paymentService.listAllActive();
    }
}