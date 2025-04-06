package com.chilo_tech.gestion_webinaire.services.pollService;

import com.chilo_tech.gestion_webinaire.model.Poll;

import java.util.List;

public interface IPollService {
    Poll ajouterPoll(Poll poll);

    List<Poll> ajouterPoll(List<Poll> polls);

    Poll modifierPoll(Poll poll, int id);

    void supprimerPoll(int id);

    Poll afficherPoll(int id);

    List<Poll> afficherPoll();
}
