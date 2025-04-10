package com.chilo_tech.gestion_webinaire.dto;

import java.sql.Timestamp;

public record UtilisateurResponse(
        int id,
        String nomUtilisateur,
        String email,
        Timestamp dateCreation,
        String role
) {
}
