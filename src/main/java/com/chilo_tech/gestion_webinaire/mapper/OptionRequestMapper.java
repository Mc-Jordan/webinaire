package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.OptionRequest;
import com.chilo_tech.gestion_webinaire.model.Option;
import com.chilo_tech.gestion_webinaire.model.Poll;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OptionRequestMapper implements Function<OptionRequest, Option> {
    /**
     *j Applies this function to the given argument.
     *
     * @param optionRequest the function argument
     * @return the function result
     */
    @Override
    public Option apply(OptionRequest optionRequest) {
        return new Option(
                0,
                optionRequest.description(),
                Poll.builder().id(optionRequest.idQuestion()).build()
        );
    }
}
