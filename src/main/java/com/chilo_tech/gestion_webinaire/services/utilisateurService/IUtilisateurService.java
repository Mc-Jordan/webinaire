package com.chilo_tech.gestion_webinaire.services.utilisateurService;

import com.chilo_tech.gestion_webinaire.dto.UtilisateurResponse;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;

import java.util.List;

public interface IUtilisateurService {

    UtilisateurResponse ajouterUtililsateur(Utilisateur utililsateur);

    List<UtilisateurResponse> ajouterUtililsateur(List<Utilisateur> utililsateurs);

    UtilisateurResponse modifierUtililsateur(Utilisateur utililsateur, int id);

    void supprimerUtilisateur(int id);

    List<UtilisateurResponse> afficherUtililsateur();

    UtilisateurResponse afficherUtililsateur(int id);
}
