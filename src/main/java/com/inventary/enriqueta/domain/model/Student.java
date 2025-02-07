package com.inventary.enriqueta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document(collection = "student")
public class Student {
    @Id
    private String id;
    private String uid;
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String gender;
    private LocalDate birthDate;
    private String baptism;
    private String communion;
    private String birthPlace;
    private String role;
    private String email;
    private String password;
    private String level;
    private String grade;
    private String section;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
