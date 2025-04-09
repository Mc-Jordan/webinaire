package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.OptionResponse;
import com.chilo_tech.gestion_webinaire.model.Option;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;


@Component
public class OptionResponseMapper implements Function<Option, OptionResponse> {
    /**
     * Applies this function to the given argument.
     *
     * @param option the function argument
     * @return the function result
     */
    @Override
    public OptionResponse apply(Option option) {
        return new OptionResponse(
                Map.of("idPoll",option.getPoll().getId()),
                Map.of("idOption",option.getId(),"description",option.getDescription())
        );
    }
}
