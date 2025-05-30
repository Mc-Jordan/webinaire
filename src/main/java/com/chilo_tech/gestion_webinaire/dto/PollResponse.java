package com.chilo_tech.gestion_webinaire.dto;

import com.chilo_tech.gestion_webinaire.model.Utilisateur;

import java.sql.Timestamp;
import java.util.Map;
import java.util.stream.Stream;

public record PollResponse(
        int id,
        String question,
        Timestamp dateCreation,
        boolean isMultipleOption,
        Utilisateur utilisateur,
        Stream<Map<String, Object>> options
){
}
