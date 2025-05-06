package com.chilo_tech.gestion_webinaire.dto;

import java.util.List;

public record BackendRequestVote(
        List<Contact> contacts,
        List<Question> questions,
        List<Reponse> reponses
) {
    public record Contact(int contacts_id) {}
    public record Question(int questions_id) {}
    public record Reponse(int answers_id) {}
}
