package com.chilo_tech.gestion_webinaire.controllers;

import com.chilo_tech.gestion_webinaire.dto.ContactRequest;
import com.chilo_tech.gestion_webinaire.dto.UtilisateurRequest;
import com.chilo_tech.gestion_webinaire.dto.UtilisateurResponse;
import com.chilo_tech.gestion_webinaire.mapper.UtilisateurRequestMapper;
import com.chilo_tech.gestion_webinaire.restserver.IBackOfficeClient;
import com.chilo_tech.gestion_webinaire.services.utilisateurService.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/utilisateurs")
//@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/contact")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurRequestMapper utilisateurRequestMapper;

    private final IBackOfficeClient iBackOfficeClient;

    @Value("${backoffice.api.token}")
    private String TOKEN;

    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurRequestMapper utilisateurRequestMapper, IBackOfficeClient iBackOfficeClient) {
        this.utilisateurService = utilisateurService;
        this.utilisateurRequestMapper = utilisateurRequestMapper;
        this.iBackOfficeClient = iBackOfficeClient;
    }


    @PostMapping
    public ResponseEntity<?> createConctact(@RequestBody ContactRequest contactRequest){
        return iBackOfficeClient.createUser("Bearer "+TOKEN, contactRequest);
    }

//    @PostMapping
//    public ResponseEntity<?> ajoutUtilisateur(@RequestBody UtilisateurRequest utilisateurRequest) {
//        return new ResponseEntity<>(this.utilisateurService.ajouterutilisateur(this.utilisateurRequestMapper.apply(utilisateurRequest)), HttpStatus.CREATED);
//    }
//
//
    @PostMapping("/all")
    public ResponseEntity<List<?>> ajoutUtilisateurs(@RequestBody List<UtilisateurRequest> utilisateursRequests) {
        return new ResponseEntity<>(this.utilisateurService.ajouterutilisateur(utilisateursRequests.stream().map(this.utilisateurRequestMapper).toList()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> afficheUtilisateur() {
        return new ResponseEntity<>(this.utilisateurService.afficherutilisateur(), HttpStatus.FOUND);
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<?> afficheUtilisateur(@PathVariable int id) {
        return new ResponseEntity<>(this.utilisateurService.afficherutilisateur(id), HttpStatus.FOUND);
    }

    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<UtilisateurResponse> modifierUtilisateur(@PathVariable int id, @RequestBody UtilisateurRequest utilisateurRequest) {
        return new ResponseEntity<>(this.utilisateurService.modifierutilisateur(this.utilisateurRequestMapper.apply(utilisateurRequest), id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable int id) {
        this.utilisateurService.supprimerUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
