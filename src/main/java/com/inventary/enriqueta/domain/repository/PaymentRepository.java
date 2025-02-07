package com.inventary.enriqueta.domain.repository;

import com.inventary.enriqueta.domain.model.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {


}
