package com.inventary.enriqueta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttorneyDTO {
    private String idAttorney;
    private String names;
    private String surnames;
    private String sex;
    private String birth_date;
    private String baptism;
    private String first_Communion;
    private String confirmation;
    private String marriage;
    private String relationship;
    private String role;
    private String email;
    private String password;
    private String cellphone;
    private String address;
    private String documentType;
    private String documentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
