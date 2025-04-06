package com.chilo_tech.gestion_webinaire.repositories;

import com.chilo_tech.gestion_webinaire.model.Utililsateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtiliseurRepository extends JpaRepository<Utililsateur, Integer> {
    boolean existsByEmail(String email);
    boolean existsBynomUtilisateur(String nomUtilisateur);
}
