package com.chilo_tech.gestion_webinaire.dto;

public record VoteRequest(
        int utilisateur,
        int poll,
        int option

) {
}
