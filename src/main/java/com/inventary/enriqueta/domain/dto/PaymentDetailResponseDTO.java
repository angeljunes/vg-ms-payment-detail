package com.inventary.enriqueta.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDetailResponseDTO {
    private String id;
    private PaymentDTO payment;
    private AttorneyDTO attorney;
    private StudentDTO student;
    private String amount;
    private LocalDate dueDate;
    private LocalDate date;
    private String paymentType;
    private String status;
}
