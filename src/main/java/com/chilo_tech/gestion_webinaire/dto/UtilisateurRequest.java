package com.chilo_tech.gestion_webinaire.dto;

public record UtilisateurRequest(
        String nomUtilisateur,
        String email,
        String motDePasse
) {
}
