package com.inventary.enriqueta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String idStudent;
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
