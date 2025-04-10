package com.chilo_tech.gestion_webinaire.services.utilisateurService;

import com.chilo_tech.gestion_webinaire.dto.UtilisateurResponse;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;

import java.util.List;

public interface IUtilisateurService {

    UtilisateurResponse ajouterutilisateur(Utilisateur utilisateur);

    List<UtilisateurResponse> ajouterutilisateur(List<Utilisateur> utilisateurs);

    UtilisateurResponse modifierutilisateur(Utilisateur utilisateur, int id);

    void supprimerUtilisateur(int id);

    List<UtilisateurResponse> afficherutilisateur();

    UtilisateurResponse afficherutilisateur(int id);
}
