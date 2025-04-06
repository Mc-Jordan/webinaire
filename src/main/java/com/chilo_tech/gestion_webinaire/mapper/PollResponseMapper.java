package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.PollResponse;
import com.chilo_tech.gestion_webinaire.model.Poll;

import java.util.function.Function;

public class PollResponseMapper implements Function<Poll, PollResponse> {
    /**
     * Applies this function to the given argument.
     *
     * @param poll the function argument
     * @return the function result
     */
    @Override
    public PollResponse apply(Poll poll) {
        return null;
    }
}
