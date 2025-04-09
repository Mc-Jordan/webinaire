package com.chilo_tech.gestion_webinaire.services.optionService;

import com.chilo_tech.gestion_webinaire.dto.OptionResponse;
import com.chilo_tech.gestion_webinaire.model.Option;

import java.util.List;

public interface IOptionService {

    OptionResponse ajouterOption(Option option);
    List<OptionResponse> ajouterOption(List<Option> options);
    OptionResponse modifierOption(int id, Option option);
    void supprimerOption(int id);
    OptionResponse afficherOption(int id);
    List<OptionResponse> afficherOption();
}
