package com.chilo_tech.gestion_webinaire.services.pollService;

import com.chilo_tech.gestion_webinaire.dto.PollResponse;
import com.chilo_tech.gestion_webinaire.model.Poll;

import java.util.List;

public interface IPollService {
    PollResponse ajouterPoll(Poll poll);

    List<PollResponse> ajouterPoll(List<Poll> polls);

    PollResponse modifierPoll(Poll poll, int id);

    void supprimerPoll(int id);

    PollResponse afficherPoll(int id);

    List<PollResponse> afficherPoll();
}
