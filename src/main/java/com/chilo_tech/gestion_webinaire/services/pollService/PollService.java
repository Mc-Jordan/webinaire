package com.chilo_tech.gestion_webinaire.services.pollService;

import com.chilo_tech.gestion_webinaire.dto.PollResponse;
import com.chilo_tech.gestion_webinaire.mapper.PollResponseMapper;
import com.chilo_tech.gestion_webinaire.model.Option;
import com.chilo_tech.gestion_webinaire.model.Poll;
import com.chilo_tech.gestion_webinaire.repositories.OptionRepository;
import com.chilo_tech.gestion_webinaire.repositories.PollRepository;
import com.chilo_tech.gestion_webinaire.repositories.UtiliseurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PollService implements IPollService {

    private final PollResponseMapper pollResponseMapper;
    private final PollRepository pollRepository;
    private final UtiliseurRepository utiliseurRepository;
    private final OptionRepository optionRepository;

    /**
     * @param poll
     * @return
     */
    @Override
    public PollResponse ajouterPoll(Poll poll) {
        System.out.println(poll);
        this.isPollValid(poll);
        return this.pollResponseMapper.apply(this.pollRepository.saveAndFlush(poll));
    }

    /**
     * @param polls
     * @return
     */
    @Override
    public List<PollResponse> ajouterPoll(List<Poll> polls) {
        for (Poll poll : polls) {
            this.isPollValid(poll);
        }
        return this.pollRepository.saveAllAndFlush(polls).stream().map(this.pollResponseMapper).toList();

    }

    /**
     * @param poll
     * @param id
     * @return
     */
    @Override
    public PollResponse modifierPoll(Poll poll, int id) {
        Poll pollOptional = getPollOptional(id);
        this.isPollValid(poll);
        pollOptional.setMultipleOption(poll.isMultipleOption());
        pollOptional.setQuestion(poll.getQuestion());
        pollOptional.setUtilisateur(poll.getUtilisateur());

        return this.pollResponseMapper.apply(this.pollRepository.saveAndFlush(pollOptional));
    }

    public Option modifierPoll(Option option) {
        Poll pollOptional = getPollOptional(option.getPoll().getId());
        pollOptional.getOptions().add(option);
        return this.pollRepository.saveAndFlush(pollOptional)
                .getOptions()
                .get(pollOptional.getOptions().size() - 1);
    }

    public List<Option> modifierPoll(List<Option> options) {
        List<Poll> pollsAModifier = this.pollRepository.findAllById(options.stream().map(option -> option.getPoll().getId()).toList());
        int index = 0;
        for (Poll poll : pollsAModifier) {
            for (Option option : options) {
                if (poll.getId() == option.getPoll().getId()) {
                    pollsAModifier.get(index).getOptions().add(option);
                }
            }
            index++;
        }
        return this.pollRepository.saveAllAndFlush(pollsAModifier)
                .stream().map(poll -> poll.getOptions().get(0)).toList();
    }

    @Transactional
    public boolean deleteOption(Option option) {
        Poll poll = getPollOptional(option.getPoll().getId());
        poll.getOptions().remove(this.optionRepository.findById(option.getId()).orElseThrow(() -> new RuntimeException("Cette option est introuvable dans la base de donnée")));
        this.pollRepository.save(poll);
        return true;
    }

    /**
     * @param id
     */
    @Override
    public void supprimerPoll(int id) {
        Poll pollOptional = getPollOptional(id);
        this.pollRepository.delete(pollOptional);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PollResponse afficherPoll(int id) {
        Poll pollOptional = getPollOptional(id);
        return this.pollResponseMapper.apply(pollOptional);
    }

    /**
     * @return
     */
    @Override
    public List<PollResponse> afficherPoll() {
        return this.pollRepository.findAll().stream().map(this.pollResponseMapper).toList();
    }


    private Poll getPollOptional(int id) {
        return this.pollRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Aucune question n'existe avec cet identifiant"));
    }


    private boolean isPollValid(Poll poll) {
        if (poll.getQuestion().isEmpty()) {
            throw new RuntimeException("Les données sont incompletes car la question en elle-même n'est pas defini");
        } else if (poll.getOptions().size() < 2) {
            throw new RuntimeException("Il n'y a pas assez de propositions dans les Options pour la question: le minimum est deux(2)");
        } else if (this.utiliseurRepository.findById(poll.getUtilisateur().getId()).isEmpty()) {
            throw new RuntimeException("Veuillez Spécifier un utilisateur valide comme createur de la question");
        }
        int index = 0;
        for (Option option : poll.getOptions()) {
            if (option.getDescription().isEmpty()) {
                throw new RuntimeException("Les données sont incompletes car la reponse en elle-même n'est pas defini");
            }
            poll.getOptions().get(index).setPoll(poll);
            index++;
        }
        poll.setDateCreation(Timestamp.from(Instant.now()));
        return true;
    }
}
