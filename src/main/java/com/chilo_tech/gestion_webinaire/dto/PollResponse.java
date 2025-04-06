package com.chilo_tech.gestion_webinaire.dto;

import com.chilo_tech.gestion_webinaire.model.Utililsateur;

import java.sql.Timestamp;
import java.util.Map;

public record PollResponse(
        int id,
        String question,
        Timestamp dateCreation,
        boolean isMultipleOption,
        Utililsateur utililsateur,
        Map<Object,Object> options
){
}
