package com.chilo_tech.gestion_webinaire.controllers;

import com.chilo_tech.gestion_webinaire.model.Utililsateur;
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
//ghp_qZt
//uXUYOUS8ic

    private final UtilisateurService utilisateurService;

//riAhw83lst
//vXNjep715Pqgn
    @PostMapping
    public ResponseEntity<Utililsateur> ajoutUtilisateur(@RequestBody Utililsateur utililsateur){
        return new ResponseEntity<>(this.utilisateurService.ajouterUtililsateur(utililsateur), HttpStatus.CREATED);
    }


    @PostMapping("/all")
    public ResponseEntity<List<Utililsateur>> ajoutUtilisateurs(@RequestBody List<Utililsateur> utililsateurs){
        return new ResponseEntity<>(this.utilisateurService.ajouterUtililsateur(utililsateurs), HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<Utililsateur>> afficheUtilisateur(){
        return new ResponseEntity<>(this.utilisateurService.afficherUtililsateur(), HttpStatus.FOUND);
    }

    @GetMapping("/utilisateur/{id}")
    public  ResponseEntity<Utililsateur> afficheUtilisateur(@PathVariable int id){
        return new ResponseEntity<>(this.utilisateurService.afficherUtililsateur(id), HttpStatus.FOUND);
    }

    @PutMapping("/utilisateur/{id}")
    public  ResponseEntity<Utililsateur> modifierUtilisateur(@PathVariable int id, @RequestBody Utililsateur utililsateur){
        return new ResponseEntity<>(this.utilisateurService.modifierUtililsateur(utililsateur,id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/utilisateur/{id}")
    public  ResponseEntity<Void> supprimerUtilisateur(@PathVariable int id){
        this.utilisateurService.supprimerUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
