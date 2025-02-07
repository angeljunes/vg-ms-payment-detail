package com.inventary.enriqueta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String idPayment;
    private ManagerDTO manager; // Almacena detalles completos del Manager
    private String description;
    private LocalDate dueDate;
    private LocalDate date;
    private String amount;
    private String status;
    private String className;
}
