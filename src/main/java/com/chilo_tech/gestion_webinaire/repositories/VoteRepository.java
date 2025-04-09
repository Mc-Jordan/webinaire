package com.chilo_tech.gestion_webinaire.repositories;

import com.chilo_tech.gestion_webinaire.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
