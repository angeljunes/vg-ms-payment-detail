package com.inventary.enriqueta.application.service.impl;

import com.inventary.enriqueta.domain.dto.PaymentDTO;
import com.inventary.enriqueta.domain.dto.AttorneyDTO;
import com.inventary.enriqueta.domain.dto.StudentDTO;
import com.inventary.enriqueta.domain.model.PaymentDetail;
import com.inventary.enriqueta.domain.repository.PaymentDetailRepository;
import com.inventary.enriqueta.application.service.PaymentDetailService;
import com.inventary.enriqueta.application.service.AttorneyService;
import com.inventary.enriqueta.application.service.StudentService;
import com.inventary.enriqueta.application.service.PaymentService;
import com.inventary.enriqueta.application.util.StatusConstants;
import com.inventary.enriqueta.application.webClient.AuthServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {

    private final PaymentDetailRepository paymentDetailRepository;
    private final AttorneyService attorneyService;
    private final StudentService studentService;
    private final PaymentService paymentService;
    private final WebClient.Builder webClientBuilder;
    private final AuthServiceClient authServiceClient;

    @Autowired
    public PaymentDetailServiceImpl(PaymentDetailRepository paymentDetailRepository, AttorneyService attorneyService, StudentService studentService, PaymentService paymentService, WebClient.Builder webClientBuilder, AuthServiceClient authServiceClient) {
        this.paymentDetailRepository = paymentDetailRepository;
        this.attorneyService = attorneyService;
        this.studentService = studentService;
        this.paymentService = paymentService;
        this.webClientBuilder = webClientBuilder;
        this.authServiceClient = authServiceClient;
    }

    @Override
    public Flux<PaymentDetail> listAll() {
        return paymentDetailRepository.findAll();
    }

    @Override
    public Mono<PaymentDetail> findById(String id) {
        return paymentDetailRepository.findById(id);
    }

    @Override
    public Flux<PaymentDetail> findByStatus(String status) {
        return paymentDetailRepository.findByStatus(status);
    }

    @Override
    public Mono<PaymentDetail> createPaymentDetail(PaymentDetail paymentDetail) {
        return attorneyService.findById(paymentDetail.getAttorney_id())
                .switchIfEmpty(Mono.error(new RuntimeException("Manager ID no encontrado: " + paymentDetail.getAttorney_id())))
                .flatMap(attorney -> studentService.findById(paymentDetail.getStudent_id())
                        .switchIfEmpty(Mono.error(new RuntimeException("Student ID no encontrado: " + paymentDetail.getStudent_id())))
                        .flatMap(student -> paymentService.findById(paymentDetail.getPayment_id())
                                .switchIfEmpty(Mono.error(new RuntimeException("Payment ID no encontrado: " + paymentDetail.getPayment_id())))
                                .flatMap(payment -> paymentDetailRepository.save(paymentDetail))
                        )
                );
    }

    @Override
    public Mono<PaymentDetail> updatePaymentDetail(String id, PaymentDetail paymentDetail) {
        return paymentDetailRepository.findById(id)
                .flatMap(existingPaymentDetail -> {
                    existingPaymentDetail.setAmount(paymentDetail.getAmount());
                    existingPaymentDetail.setDate(paymentDetail.getDate());
                    existingPaymentDetail.setPaymentType(paymentDetail.getPaymentType());
                    existingPaymentDetail.setStatus(paymentDetail.getStatus());
                    existingPaymentDetail.setAttorney_id(paymentDetail.getAttorney_id());
                    existingPaymentDetail.setAttorneyFullName(paymentDetail.getAttorneyFullName());
                    existingPaymentDetail.setPayment_id(paymentDetail.getPayment_id());
                    existingPaymentDetail.setPaymentFullName(paymentDetail.getPaymentFullName());
                    existingPaymentDetail.setStudent_id(paymentDetail.getStudent_id());
                    existingPaymentDetail.setStudentFullName(paymentDetail.getStudentFullName());

                    return paymentDetailRepository.save(existingPaymentDetail);
                });
    }

    @Override
    public Mono<PaymentDetail> deletePaymentDetail(String id) {
        return paymentDetailRepository.findById(id)
                .flatMap(existingPaymentDetail -> {
                    existingPaymentDetail.setStatus(StatusConstants.INACTIVE);
                    return paymentDetailRepository.save(existingPaymentDetail);
                });
    }

    @Override
    public Mono<PaymentDetail> reactivatePaymentDetail(String id) {
        return paymentDetailRepository.findById(id)
                .flatMap(existingPaymentDetail -> {
                    if (StatusConstants.INACTIVE.equals(existingPaymentDetail.getStatus())) {
                        existingPaymentDetail.setStatus(StatusConstants.ACTIVE);
                        return paymentDetailRepository.save(existingPaymentDetail);
                    }
                    return Mono.just(existingPaymentDetail);
                });
    }

    // Nuevos métodos para listar activos e inactivos
    @Override
    public Flux<PaymentDetail> listAllActive() {
        return paymentDetailRepository.findAll()
                .filter(paymentDetail -> StatusConstants.ACTIVE.equals(paymentDetail.getStatus()));
    }

    @Override
    public Flux<PaymentDetail> listAllInactive() {
        return paymentDetailRepository.findAll()
                .filter(paymentDetail -> StatusConstants.INACTIVE.equals(paymentDetail.getStatus()));
    }

    // Métodos privados para obtener detalles desde otros microservicios
    private Mono<PaymentDTO> getPaymentDetails(String paymentId) {
        return webClientBuilder.build()
                .get()
                .uri("https://vg-ms-payment-production.up.railway.app/api/payments/" + paymentId)
                .retrieve()
                .bodyToMono(PaymentDTO.class);
    }

    private Mono<AttorneyDTO> getAttorneyDetails(String attorneyId) {
        return webClientBuilder.build()
                .get()
                .uri("https://vg-ms-attorney-v1-production.up.railway.app/api/v1/public/attorney/" + attorneyId)
                .retrieve()
                .bodyToMono(AttorneyDTO.class);
    }

    private Mono<StudentDTO> getStudentDetails(String studentId) {
        return webClientBuilder.build()
                .get()
                .uri("https://vg-ms-student-production-48e2.up.railway.app/api/v1/public/student/" + studentId)
                .retrieve()
                .bodyToMono(StudentDTO.class);
    }

    @Override
    public Mono<Boolean> validateTokenAndRoles(String token, List<String> requiredRoles) {
        return authServiceClient.validateToken(token)
                .flatMap(validationResponse -> {
                    if (validationResponse.isValid() && requiredRoles.contains(validationResponse.getRole())) {
                        return Mono.just(true);
                    }
                    return Mono.just(false);
                });
    }

}
