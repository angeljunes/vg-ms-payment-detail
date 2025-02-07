package com.inventary.enriqueta.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {
    private String id;
    private String uid;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String gender;
    private String address;
    private String birthPlace;
    private String email;
    private String role;        // Agregamos el campo role
    private String password;    // Agregamos el campo password
    private String status;
    private LocalDateTime createdAt;  // Agregamos el campo createdAt
    private LocalDateTime updatedAt;  // Agregamos el campo updatedAt
}
