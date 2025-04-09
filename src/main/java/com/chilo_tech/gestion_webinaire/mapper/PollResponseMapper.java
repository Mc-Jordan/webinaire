package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.PollResponse;
import com.chilo_tech.gestion_webinaire.model.Poll;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class PollResponseMapper implements Function<Poll, PollResponse> {
    /**
     * Applies this function to the given argument.
     *
     * @param poll the function argument
     * @return the function result
     */
    @Override
    public PollResponse apply(Poll poll) {
        return new PollResponse(
                poll.getId(),
                poll.getQuestion(),
                poll.getDateCreation(),
                poll.isMultipleOption(),
                poll.getUtililsateur(),
                poll.getOptions().stream().map(option -> Map.of("id",option.getId(),"description",option.getDescription()))
        );
    }
}
