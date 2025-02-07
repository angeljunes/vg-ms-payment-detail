package com.inventary.enriqueta.domain.dto;

public class TokenValidation {
    private boolean valid;
    private String role;

    public TokenValidation(boolean valid, String role) {
        this.valid = valid;
        this.role = role;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
