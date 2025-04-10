package com.chilo_tech.gestion_webinaire.dto;

import com.chilo_tech.gestion_webinaire.model.Utilisateur;

import java.sql.Timestamp;
import java.util.Map;

public record VoteResponse(
        int id,
        Timestamp dateVote,
        Map<String, Utilisateur> utilisateur,
        Map<String, PollResponse> poll,
        Map<String, OptionResponse> option

) {
}
