package com.chilo_tech.gestion_webinaire.dto;

public record ContactRequest(
        String firstName,
        String lastName,
        String email,
        String phone,
        String message
) {
}
