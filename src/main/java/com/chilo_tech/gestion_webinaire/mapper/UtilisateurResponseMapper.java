package com.chilo_tech.gestion_webinaire.mapper;

import com.chilo_tech.gestion_webinaire.dto.UtilisateurResponse;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UtilisateurResponseMapper implements Function<Utilisateur, UtilisateurResponse> {
    /**
     * Applies this function to the given argument.
     *
     * @param utilisateur the function argument
     * @return the function result
     */
    @Override
    public UtilisateurResponse apply(Utilisateur utilisateur) {
        return new UtilisateurResponse(
                utilisateur.getId(),
                utilisateur.getNomUtilisateur(),
                utilisateur.getEmail(),
                utilisateur.getDateCreation(),
                utilisateur.getRole()
        );
    }
}
