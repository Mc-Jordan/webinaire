package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.PollRequest;
import com.chilo_tech.gestion_webinaire.model.Option;
import com.chilo_tech.gestion_webinaire.model.Poll;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PollRequestMapper implements Function<PollRequest, Poll> {
    /**
     * Applies this function to the given argument.
     *
     * @param pollRequest the function argument
     * @return the function result
     */
    @Override
    public Poll apply(PollRequest pollRequest) {
        return new Poll(0,
                pollRequest.question(),
                null,
                pollRequest.multipleOption(),
                Utilisateur.builder()
                        .id(pollRequest.utilisateur())
                        .build(),
                pollRequest.options().stream().map(option -> Option.builder()
                        .description(option.get("description"))
                        .build()
                ).toList()
        );
    }
}
