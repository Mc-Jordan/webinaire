package com.chilo_tech.gestion_webinaire.dto;

import java.util.List;
import java.util.Map;

public record PollRequest(
        String question,
        boolean multipleOption,
        int utilisateur,
        List<Map<String, String>> options
) {

}
