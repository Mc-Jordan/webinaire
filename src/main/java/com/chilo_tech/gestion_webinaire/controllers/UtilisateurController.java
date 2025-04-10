package com.chilo_tech.gestion_webinaire.controllers;

import com.chilo_tech.gestion_webinaire.dto.UtilisateurRequest;
import com.chilo_tech.gestion_webinaire.dto.UtilisateurResponse;
import com.chilo_tech.gestion_webinaire.mapper.UtilisateurRequestMapper;
import com.chilo_tech.gestion_webinaire.services.utilisateurService.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@AllArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurRequestMapper utilisateurRequestMapper;

    @PostMapping
    public ResponseEntity<?> ajoutUtilisateur(@RequestBody UtilisateurRequest utilisateurRequest) {
        return new ResponseEntity<>(this.utilisateurService.ajouterUtililsateur(this.utilisateurRequestMapper.apply(utilisateurRequest)), HttpStatus.CREATED);
    }


    @PostMapping("/all")
    public ResponseEntity<List<?>> ajoutUtilisateurs(@RequestBody List<UtilisateurRequest> utililsateursRequests) {
        return new ResponseEntity<>(this.utilisateurService.ajouterUtililsateur(utililsateursRequests.stream().map(this.utilisateurRequestMapper).toList()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> afficheUtilisateur() {
        return new ResponseEntity<>(this.utilisateurService.afficherUtililsateur(), HttpStatus.FOUND);
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<?> afficheUtilisateur(@PathVariable int id) {
        return new ResponseEntity<>(this.utilisateurService.afficherUtililsateur(id), HttpStatus.FOUND);
    }

    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<UtilisateurResponse> modifierUtilisateur(@PathVariable int id, @RequestBody UtilisateurRequest utililsateurRequest) {
        return new ResponseEntity<>(this.utilisateurService.modifierUtililsateur(this.utilisateurRequestMapper.apply(utililsateurRequest), id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable int id) {
        this.utilisateurService.supprimerUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
