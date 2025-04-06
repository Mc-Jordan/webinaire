package com.chilo_tech.gestion_webinaire.services.utilisateurService;

import com.chilo_tech.gestion_webinaire.model.Utililsateur;
import com.chilo_tech.gestion_webinaire.repositories.UtiliseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurService implements IUtilisateurService{
    private final UtiliseurRepository utiliseurRepository;


    //ajout d'un seul utilisateur dans la base de données
    @Override
    public Utililsateur ajouterUtililsateur(Utililsateur utililsateur) {
        if (utililsateur.getNomUtilisateur().isEmpty()
                || utililsateur.getEmail().isEmpty()
                || utililsateur.getMotDePasse().isEmpty()) {
            throw new RuntimeException("Les Champs ne sont pas correctement remplis");
        }
        if (!utiliseurRepository.existsByEmail(utililsateur.getEmail())
                && !utiliseurRepository.existsBynomUtilisateur(utililsateur.getNomUtilisateur())) {
            utililsateur.setDateCreation(Timestamp.from(Instant.now()));
            return this.utiliseurRepository.save(utililsateur);
        } else {
            throw new RuntimeException("Cet utilisateur existe déjà");
        }
    }

    //ajout de plusieurs utilisateus dans la base de données
    @Override
    public List<Utililsateur> ajouterUtililsateur(List<Utililsateur> utililsateurs) {
        for (Utililsateur utililsateur : utililsateurs) {
            if (utililsateur.getNomUtilisateur().isEmpty()
                    || utililsateur.getEmail().isEmpty()) {
                throw new RuntimeException("Les Champs ne sont pas correctement remplis");
            }

            if (utiliseurRepository.existsByEmail(utililsateur.getEmail())
                    && utiliseurRepository.existsBynomUtilisateur(utililsateur.getNomUtilisateur())) {
                throw new RuntimeException("Un utilisateur existe déjà");
            }
            utililsateur.setDateCreation(Timestamp.from(Instant.now()));
        }
        return this.utiliseurRepository.saveAll(utililsateurs);
    }

    //methode pour modifier les informations d'un utilisateur
    @Override
    public Utililsateur modifierUtililsateur(Utililsateur utililsateur, int id) {
        Utililsateur utililsateurTrouve = this.utiliseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucun utilisateur avec cet identifiant"));

        utililsateurTrouve.setMotDePasse(utililsateur.getMotDePasse().isEmpty() ? utililsateurTrouve.getMotDePasse() : utililsateur.getMotDePasse());
        return this.utiliseurRepository.saveAndFlush(utililsateurTrouve);
    }

    //mehode pour la suppression d'un utilisateur
    @Override
    public void supprimerUtilisateur(int id) {
        if (this.utiliseurRepository.existsById(id)) {
            this.utiliseurRepository.deleteById(id);
        }
    }

    //methode pour afficher la liste compplète des utilisateurs
    @Override
    public List<Utililsateur> afficherUtililsateur() {
        return this.utiliseurRepository.findAll();
    }


    //methode pour afficher les informations sur un seul utilisateur
    @Override
    public Utililsateur afficherUtililsateur(int id) {
        return this.utiliseurRepository.findById(id).orElseThrow(() -> new RuntimeException("C'est identifiant ne correspond à aucun utlisateur"));
    }
}
