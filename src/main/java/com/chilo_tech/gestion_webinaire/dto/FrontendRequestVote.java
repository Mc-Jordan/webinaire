package com.chilo_tech.gestion_webinaire.dto;

import java.util.List;

public record FrontendRequestVote(
        List<Integer> contacts,
        List<Integer> questions,
        List<Integer> reponses
) {}

