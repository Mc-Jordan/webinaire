package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.UtilisateurRequest;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.Function;

@Component
public class UtilisateurRequestMapper implements Function<UtilisateurRequest, Utilisateur> {
    /**
     * Applies this function to the given argument.
     *
     * @param utilisateurRequest the function argument
     * @return the function result
     */
    @Override
    public Utilisateur apply(UtilisateurRequest utilisateurRequest) {
        return new Utilisateur(0,
                utilisateurRequest.nomUtilisateur(),
                utilisateurRequest.email(),
                utilisateurRequest.motDePasse(),
                Timestamp.from(Instant.now()),
                "standard"
        );
    }
}
