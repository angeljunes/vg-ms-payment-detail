package com.inventary.enriqueta.presentation.controller;

import com.inventary.enriqueta.application.service.PaymentDetailService;
import com.inventary.enriqueta.domain.model.PaymentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/shared/payment-details${api.version}")
public class PaymentDetailUserController {

    private final PaymentDetailService paymentDetailService;

    @Autowired
    public PaymentDetailUserController(PaymentDetailService paymentDetailService) {
        this.paymentDetailService = paymentDetailService;
    }

    // Obtener todos los PaymentDetails
    @GetMapping
    public Flux<PaymentDetail> getAllPaymentDetails() {
        return paymentDetailService.listAll();
    }

    // Obtener PaymentDetails activos
    @GetMapping("/actives")
    public Flux<PaymentDetail> getAllActivePaymentDetails() {
        return paymentDetailService.listAllActive();
    }

    // Obtener PaymentDetails inactivos
    @GetMapping("/inactives")
    public Flux<PaymentDetail> getAllInactivePaymentDetails() {
        return paymentDetailService.listAllInactive();
    }

    // Obtener un PaymentDetail por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<PaymentDetail>> getPaymentDetailById(@PathVariable String id) {
        return paymentDetailService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Crear un nuevo PaymentDetail
    @PostMapping
    public Mono<PaymentDetail> createPaymentDetail(@RequestBody PaymentDetail paymentDetail) {
        System.out.println("Creando un nuevo PaymentDetail: " + paymentDetail);
        return paymentDetailService.createPaymentDetail(paymentDetail);
    }

    // Actualizar un PaymentDetail existente
    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<PaymentDetail>> updatePaymentDetail(@PathVariable String id, @RequestBody PaymentDetail paymentDetail) {
        return paymentDetailService.updatePaymentDetail(id, paymentDetail)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Eliminar (cambiar el estado a inactivo) un PaymentDetail
    @PutMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deletePaymentDetail(@PathVariable String id) {
        return paymentDetailService.deletePaymentDetail(id)
                .map(deleted -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Reactivar un PaymentDetail (cambiar de inactivo a activo)
    @PutMapping("/reactivate/{id}")
    public Mono<ResponseEntity<PaymentDetail>> reactivatePaymentDetail(@PathVariable String id) {
        return paymentDetailService.reactivatePaymentDetail(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Obtener PaymentDetails por estado
    @GetMapping("/status/A")
    public Flux<PaymentDetail> getInventoriesWithStatusM() {
        return paymentDetailService.findByStatus("A");
    }
}