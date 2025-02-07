package com.inventary.enriqueta.domain.model;

import com.inventary.enriqueta.domain.dto.ManagerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private ManagerDTO manager;
    private String description;
    private LocalDate dueDate;
    private LocalDate date;
    private String amount;
    private String status;
    private String className;

}
