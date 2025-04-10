package com.chilo_tech.gestion_webinaire.services.voteService;

import com.chilo_tech.gestion_webinaire.dto.VoteResponse;
import com.chilo_tech.gestion_webinaire.mapper.VoteResponseMapper;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;
import com.chilo_tech.gestion_webinaire.model.Vote;
import com.chilo_tech.gestion_webinaire.repositories.PollRepository;
import com.chilo_tech.gestion_webinaire.repositories.VoteRepository;
import com.chilo_tech.gestion_webinaire.services.optionService.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService implements IVoteService {

    private final VoteRepository voteRepository;
    private final PollRepository pollRepository;
    private final OptionService optionService;
    private final VoteResponseMapper voteResponseMapper;

    /**
     * @param vote
     * @return
     */
    @Override
    public VoteResponse ajouterVote(Vote vote) {
        if (isUtilisateurEqualsCreateur(vote)) {
            throw new RuntimeException("Le createur d'une question ne peut pas effectuer un vote sur la question qu'il a lui-même créé");
        }
        vote.setPoll(this.optionService.isQuestionExist(vote.getPoll().getId()));
        vote.setOption(this.optionService.isOptionExist(vote.getOption().getId()));
        vote.setDateVote(Timestamp.from(Instant.now()));
        return this.voteResponseMapper.apply(this.voteRepository.saveAndFlush(vote));
    }

    /**
     * @param votes
     * @return
     */
    @Override
    public List<VoteResponse> ajouterVote(List<Vote> votes) {
        int index = 0;
        for (Vote vote : votes) {
            if (isUtilisateurEqualsCreateur(vote)) {
                throw new RuntimeException("Le createur d'une question ne peut pas effectuer un vote sur la question qu'il a lui-même créé");
            }
            votes.get(index).setPoll(this.optionService.isQuestionExist(vote.getPoll().getId()));
            votes.get(index).setOption(this.optionService.isOptionExist(vote.getOption().getId()));
            votes.get(index).setDateVote(Timestamp.from(Instant.now()));
            index++;
        }
        return this.voteRepository.saveAllAndFlush(votes).stream().map(this.voteResponseMapper).toList();
    }

    /**
     * @param id
     * @param vote
     * @return
     */
    @Override
    public VoteResponse modifierVote(int id, Vote vote) {
        Vote voteOptional = this.voteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Vote introuvable"));
        if (isUtilisateurEqualsCreateur(vote)) {
            throw new RuntimeException("Le createur d'une question ne peut pas effectuer un vote sur la question qu'il a lui-même créé");
        }
        voteOptional.setPoll(this.optionService.isQuestionExist(vote.getPoll().getId()));
        voteOptional.setOption(this.optionService.isOptionExist(vote.getOption().getId()));
        return this.voteResponseMapper.apply(this.voteRepository.saveAndFlush(voteOptional));
    }

    /**
     * @param id
     */
    @Override
    public void supprimerVote(int id) {
        if (this.voteRepository.existsById(id)) {
            this.voteRepository.deleteById(id);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public VoteResponse afficherVote(int id) {
        return this.voteResponseMapper.apply(this.voteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ce vote n'existe pas dans la base de données")
        ));
    }

    /**
     * @return
     */
    @Override
    public List<VoteResponse> afficherVote() {
        return this.voteRepository.findAll().stream().map(this.voteResponseMapper).toList();
    }

    private boolean isUtilisateurEqualsCreateur(Vote vote) {
        Utilisateur createur = this.pollRepository.findById(vote.getPoll().getId())
                .orElseThrow(() -> new RuntimeException("C'est utilisateur ou ce poll est introuvable dans notre base de données"))
                .getutilisateur();
        return vote.getutilisateur().getId() == createur.getId();
    }
}
