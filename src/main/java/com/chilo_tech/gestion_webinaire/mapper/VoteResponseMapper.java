package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.VoteResponse;
import com.chilo_tech.gestion_webinaire.model.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class VoteResponseMapper implements Function<Vote, VoteResponse> {
    private final OptionResponseMapper optionResponseMapper;
    private final PollResponseMapper pollResponseMapper;
    /**
     * Applies this function to the given argument.
     *
     * @param vote the function argument
     * @return the function result
     */
    @Override
    public VoteResponse apply(Vote vote) {
        return new VoteResponse(
                vote.getId(),
                vote.getDateVote(),
                Map.of("utilisateur", vote.getutilisateur()),
                Map.of("poll", this.pollResponseMapper.apply(vote.getPoll())),
                Map.of("optionChoisi",this.optionResponseMapper.apply(vote.getOption()))
        );
    }
}
