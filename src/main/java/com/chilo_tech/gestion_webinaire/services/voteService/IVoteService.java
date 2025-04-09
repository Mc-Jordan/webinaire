package com.chilo_tech.gestion_webinaire.services.voteService;

import com.chilo_tech.gestion_webinaire.dto.VoteResponse;
import com.chilo_tech.gestion_webinaire.model.Vote;

import java.util.List;

public interface IVoteService {
    VoteResponse ajouterVote(Vote vote);
    List<VoteResponse> ajouterVote(List<Vote> votes);
    VoteResponse modifierVote(int id, Vote vote);
    void supprimerVote(int id);
    VoteResponse afficherVote(int id);
    List<VoteResponse> afficherVote();
}
