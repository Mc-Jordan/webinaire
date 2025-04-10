package com.chilo_tech.gestion_webinaire.services.utilisateurService;

import com.chilo_tech.gestion_webinaire.dto.UtilisateurResponse;
import com.chilo_tech.gestion_webinaire.mapper.UtilisateurResponseMapper;
import com.chilo_tech.gestion_webinaire.model.Utilisateur;
import com.chilo_tech.gestion_webinaire.repositories.UtiliseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurService implements IUtilisateurService {
    private final UtiliseurRepository utiliseurRepository;
    private final UtilisateurResponseMapper utilisateurResponseMapper;


    //ajout d'un seul utilisateur dans la base de données
    @Override
    public UtilisateurResponse ajouterUtililsateur(Utilisateur utililsateur) {
        if (utililsateur.getNomUtilisateur().isEmpty()
                || utililsateur.getEmail().isEmpty()
                || utililsateur.getMotDePasse().isEmpty()) {
            throw new RuntimeException("Les Champs ne sont pas correctement remplis");
        }
        if (!utiliseurRepository.existsByEmail(utililsateur.getEmail())
                && !utiliseurRepository.existsBynomUtilisateur(utililsateur.getNomUtilisateur())) {
            utililsateur.setDateCreation(Timestamp.from(Instant.now()));
            return this.utilisateurResponseMapper.apply(this.utiliseurRepository.save(utililsateur));
        } else {
            throw new RuntimeException("Cet utilisateur existe déjà");
        }
    }

    //ajout de plusieurs utilisateus dans la base de données
    @Override
    public List<UtilisateurResponse> ajouterUtililsateur(List<Utilisateur> utililsateurs) {
        for (Utilisateur utililsateur : utililsateurs) {
            if (utililsateur.getNomUtilisateur().isEmpty()
                    || utililsateur.getEmail().isEmpty()) {
                throw new RuntimeException("Les Champs ne sont pas correctement remplis");
            }

            if (utiliseurRepository.existsByEmail(utililsateur.getEmail())
                    && utiliseurRepository.existsBynomUtilisateur(utililsateur.getNomUtilisateur())) {
                throw new RuntimeException("Un utilisateur existe déjà");
            }

        }
        return this.utiliseurRepository.saveAll(utililsateurs).stream().map(this.utilisateurResponseMapper).toList();
    }

    //methode pour modifier les informations d'un utilisateur
    @Override
    public UtilisateurResponse modifierUtililsateur(Utilisateur utililsateur, int id) {
        Utilisateur utililsateurTrouve = this.utiliseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucun utilisateur avec cet identifiant"));

        utililsateurTrouve.setMotDePasse(utililsateur.getMotDePasse().isEmpty() ? utililsateurTrouve.getMotDePasse() : utililsateur.getMotDePasse());
        return this.utilisateurResponseMapper.apply(this.utiliseurRepository.saveAndFlush(utililsateurTrouve));
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
    public List<UtilisateurResponse> afficherUtililsateur() {
        return this.utiliseurRepository.findAll().stream().map(this.utilisateurResponseMapper).toList();
    }


    //methode pour afficher les informations sur un seul utilisateur
    @Override
    public UtilisateurResponse afficherUtililsateur(int id) {
        return this.utilisateurResponseMapper.apply(this.utiliseurRepository.findById(id).orElseThrow(() -> new RuntimeException("C'est identifiant ne correspond à aucun utlisateur")));
    }
}
