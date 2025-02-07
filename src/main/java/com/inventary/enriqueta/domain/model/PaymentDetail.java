package com.inventary.enriqueta.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "payment_detail")
public class PaymentDetail {
    @Id
    private String id;
    private String amount;
    private LocalDate date;
    private String paymentType;
    private String status;
    private String attorney_id;
    private String attorneyFullName;
    private String payment_id;
    private String paymentFullName;
    private String student_id;
    private String studentFullName;
}
