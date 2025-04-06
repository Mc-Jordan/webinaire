package com.chilo_tech.gestion_webinaire.services.pollService;

import com.chilo_tech.gestion_webinaire.model.Option;
import com.chilo_tech.gestion_webinaire.model.Poll;
import com.chilo_tech.gestion_webinaire.repositories.PollRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PollService implements IPollService{

    private final PollRepository pollRepository;
    /**
     * @param poll 
     * @return
     */
    @Override
    public Poll ajouterPoll(Poll poll) {
        if (poll.getQuestion().isEmpty()){
            throw  new RuntimeException("Les données sont incompletes car la question en elle-même n'est pas defini");
        } else if (poll.getOptions().size()<2) {
            throw  new RuntimeException("Il n'y a pas assez de propositions dans les Options pour la question: le minimum est deux(2)");
        }
        int index=0;
            for (Option option: poll.getOptions()){
                if (option.getDescription().isEmpty()){
                    throw  new RuntimeException("Les données sont incompletes car la reponse en elle-même n'est pas defini");
                }
                poll.getOptions().get(index).setPoll(poll);
                index++;
            }
        return this.pollRepository.save(poll);
    }

    /**
     * @param polls 
     * @return
     */
    @Override
    public List<Poll> ajouterPoll(List<Poll> polls) {
        return List.of();
    }

    /**
     * @param poll 
     * @param id
     * @return
     */
    @Override
    public Poll modifierPoll(Poll poll, int id) {
        return null;
    }

    /**
     * @param id 
     */
    @Override
    public void supprimerPoll(int id) {

    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Poll afficherPoll(int id) {
        return null;
    }

    /**
     * @return 
     */
    @Override
    public List<Poll> afficherPoll() {
        return List.of();
    }
}
