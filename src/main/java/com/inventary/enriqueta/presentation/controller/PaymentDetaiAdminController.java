package com.inventary.enriqueta.presentation.controller;

import com.inventary.enriqueta.application.service.PaymentDetailService;
import com.inventary.enriqueta.domain.model.PaymentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/directives/payment-details${api.version}")
public class PaymentDetaiAdminController {

    private final PaymentDetailService paymentDetailService;

    private static final List<String> ALLOWED_ROLES = List.of("DEVELOP", "PROFESOR", "SECRETARIO", "DIRECTOR");

    @Autowired
    public PaymentDetaiAdminController(PaymentDetailService paymentDetailService) {
        this.paymentDetailService = paymentDetailService;
    }

    // Obtener todos los PaymentDetails
    @GetMapping
    public Mono<ResponseEntity<Flux<PaymentDetail>>> getAllPaymentDetailsWithAuthorization(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return Mono.just(ResponseEntity.ok(paymentDetailService.listAll()));
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Obtener PaymentDetails activos
    @GetMapping("/actives")
    public Mono<ResponseEntity<Flux<PaymentDetail>>> getAllActivePaymentDetailsWithAuthorization(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return Mono.just(ResponseEntity.ok(paymentDetailService.listAllActive()));
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Obtener PaymentDetails inactivos
    @GetMapping("/inactives")
    public Mono<ResponseEntity<Flux<PaymentDetail>>> getAllInactivePaymentDetailsWithAuthorization(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return Mono.just(ResponseEntity.ok(paymentDetailService.listAllInactive()));
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Obtener un PaymentDetail por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<PaymentDetail>> getPaymentDetailByIdWithAuthorization(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return paymentDetailService.findById(id)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Crear un nuevo PaymentDetail
    @PostMapping
    public Mono<ResponseEntity<PaymentDetail>> createPaymentDetailWithAuthorization(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PaymentDetail paymentDetail) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        System.out.println("Creando un nuevo PaymentDetail: " + paymentDetail);
                        return paymentDetailService.createPaymentDetail(paymentDetail)
                                .map(ResponseEntity::ok);
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Actualizar un PaymentDetail existente
    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<PaymentDetail>> updatePaymentDetailWithAuthorization(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String id, @RequestBody PaymentDetail paymentDetail) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return paymentDetailService.updatePaymentDetail(id, paymentDetail)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Eliminar (cambiar el estado a inactivo) un PaymentDetail
    @PutMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deletePaymentDetailWithAuthorization(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return paymentDetailService.deletePaymentDetail(id)
                                .map(deleted -> ResponseEntity.noContent().<Void>build())
                                .defaultIfEmpty(ResponseEntity.notFound().build());
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Reactivar un PaymentDetail (cambiar de inactivo a activo)
    @PutMapping("/reactivate/{id}")
    public Mono<ResponseEntity<PaymentDetail>> reactivatePaymentDetailWithAuthorization(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return paymentDetailService.reactivatePaymentDetail(id)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

    // Obtener PaymentDetails por estado
    @GetMapping("/status/A")
    public Mono<ResponseEntity<Flux<PaymentDetail>>> getInventoriesWithStatusMWithAuthorization(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        return paymentDetailService.validateTokenAndRoles(token, ALLOWED_ROLES)
                .flatMap(isValid -> {
                    if (isValid) {
                        return Mono.just(ResponseEntity.ok(paymentDetailService.findByStatus("A")));
                    } else {
                        return Mono.just(ResponseEntity.status(403).build());
                    }
                });
    }

}