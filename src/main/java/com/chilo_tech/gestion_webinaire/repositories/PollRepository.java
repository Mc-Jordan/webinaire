package com.chilo_tech.gestion_webinaire.repositories;

import com.chilo_tech.gestion_webinaire.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Integer> {
}
