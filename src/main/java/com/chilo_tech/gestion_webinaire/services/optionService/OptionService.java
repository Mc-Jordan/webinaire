package com.chilo_tech.gestion_webinaire.services.optionService;

import com.chilo_tech.gestion_webinaire.dto.OptionResponse;
import com.chilo_tech.gestion_webinaire.mapper.OptionResponseMapper;
import com.chilo_tech.gestion_webinaire.model.Option;
import com.chilo_tech.gestion_webinaire.model.Poll;
import com.chilo_tech.gestion_webinaire.repositories.OptionRepository;
import com.chilo_tech.gestion_webinaire.repositories.PollRepository;
import com.chilo_tech.gestion_webinaire.services.pollService.PollService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionService implements IOptionService {

    private final OptionRepository optionRepository;
    private final PollRepository pollRepository;
    private final OptionResponseMapper optionResponseMapper;
    private final PollService  pollService;

    /**
     * @param option
     * @return
     */
    @Override
    public OptionResponse ajouterOption(Option option) {
        option.setPoll(isQuestionExist(option.getPoll().getId()));
        if (option.getDescription().isEmpty()) {
            throw new RuntimeException("Veuillez indiquer une description");
        }
        return this.optionResponseMapper.apply(this.pollService.modifierPoll(option));
    }

    /**
     * @param options
     * @return
     */
    @Override
    public List<OptionResponse> ajouterOption(List<Option> options) {
        if (isQuestionExist(options.stream().map(option -> option.getPoll().getId()).toList())){
            for (Option option : options) {
                if (option.getDescription().isEmpty()) {
                    throw new RuntimeException("Veuillez indiquer une description");
                }
            }
            this.optionRepository.saveAllAndFlush(this.pollService.modifierPoll(options));
            return options.stream().map(this.optionResponseMapper).toList();
        }
        throw new RuntimeException("Une erreur s'est produit lors de l'ajout dans la base de donnée");
    }

    /**
     * @param id
     * @param option
     * @return
     */
    @Override
    public OptionResponse modifierOption(int id, Option option) {
        Option optionFound = this.isOptionExist(option,id);
        if (option.getDescription().isEmpty()) {
            throw new RuntimeException("Veuillez indiquer une description");
        }
        optionFound.setDescription(option.getDescription());
        return this.optionResponseMapper.apply(this.optionRepository.saveAndFlush(optionFound));
    }

    /**
     * @param id
     */
    @Override
    public void supprimerOption(int id) {
        Option option = isOptionExist(id);
        if (this.pollService.deleteOption(option)){
            this.optionRepository.deleteById(id);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public OptionResponse afficherOption(int id) {
        return this.optionResponseMapper.apply(this.isOptionExist(id));
    }

    /**
     * @return
     */
    @Override
    public List<OptionResponse> afficherOption() {
        return this.optionRepository.findAll().stream().map(this.optionResponseMapper).toList();
    }

    public Poll isQuestionExist(int id) {
        return this.pollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La question que vous avez sélectioné n'existe pas"));
    }

    public Option isOptionExist(int id) {
        return this.optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'option que vous avez sélectioné n'existe pas"));
    }

    private Option isOptionExist(Option option,int id) {
        this.pollRepository.findById(option.getPoll().getId())
                .orElseThrow(() -> new RuntimeException("La question que vous avez sélectioné n'existe pas"));
        return this.optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'option que vous avez sélectioné n'existe pas"));
    }

    private boolean isQuestionExist(List<Integer> ids) {
        this.pollRepository.findAllById(ids);
        return true;
    }
}
