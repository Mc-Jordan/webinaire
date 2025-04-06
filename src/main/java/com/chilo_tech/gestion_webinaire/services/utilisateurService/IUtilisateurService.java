package com.chilo_tech.gestion_webinaire.services.utilisateurService;

import com.chilo_tech.gestion_webinaire.model.Utililsateur;

import java.util.List;

public interface IUtilisateurService {

    Utililsateur ajouterUtililsateur(Utililsateur utililsateur);

    List<Utililsateur> ajouterUtililsateur(List<Utililsateur> utililsateurs);

    Utililsateur modifierUtililsateur(Utililsateur utililsateur, int id);

    void supprimerUtilisateur(int id);

    List<Utililsateur> afficherUtililsateur();

    Utililsateur afficherUtililsateur(int id);
}
