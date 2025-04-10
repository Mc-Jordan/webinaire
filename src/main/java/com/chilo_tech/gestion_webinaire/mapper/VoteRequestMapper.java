package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.VoteRequest;
import com.chilo_tech.gestion_webinaire.model.Option;
import com.chilo_tech.gestion_webinaire.model.Poll;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;
import com.chilo_tech.gestion_webinaire.model.Vote;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class VoteRequestMapper implements Function<VoteRequest, Vote> {
    /**
     * Applies this function to the given argument.
     *
     * @param voteRequest the function argument
     * @return the function result
     */
    @Override
    public Vote apply(VoteRequest voteRequest) {
        return new Vote(
                0,
                null,
                Utilisateur.builder().id(voteRequest.utilisateur()).build(),
                Poll.builder().id(voteRequest.poll()).build(),
                Option.builder().id(voteRequest.option()).build()
        );
    }
}
