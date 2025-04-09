package com.chilo_tech.gestion_webinaire.controllers;


import com.chilo_tech.gestion_webinaire.dto.VoteRequest;
import com.chilo_tech.gestion_webinaire.dto.VoteResponse;
import com.chilo_tech.gestion_webinaire.mapper.VoteRequestMapper;
import com.chilo_tech.gestion_webinaire.model.Utililsateur;
import com.chilo_tech.gestion_webinaire.model.Vote;
import com.chilo_tech.gestion_webinaire.services.utilisateurService.UtilisateurService;
import com.chilo_tech.gestion_webinaire.services.voteService.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final VoteRequestMapper voteRequestMapper;

    @PostMapping
    public ResponseEntity<?> ajouterVote(@RequestBody VoteRequest voteRequest){
        return new ResponseEntity<>(this.voteService.ajouterVote(this.voteRequestMapper.apply(voteRequest)), HttpStatus.CREATED);
    }


    @PostMapping("/all")
    public ResponseEntity<?> ajouterVote(@RequestBody List<VoteRequest> votesRequest){
        return new ResponseEntity<>(this.voteService.ajouterVote(votesRequest.stream().map(this.voteRequestMapper).toList()), HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<?> afficheVote(){
        return new ResponseEntity<>(this.voteService.afficherVote(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/vote/{id}")
    public  ResponseEntity<?> afficheVote(@PathVariable int id){
        return new ResponseEntity<>(this.voteService.afficherVote(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/vote/{id}")
    public  ResponseEntity<?> modifierVote(@PathVariable int id, @RequestBody VoteRequest voteRequest){
        return new ResponseEntity<>(this.voteService.modifierVote(id,this.voteRequestMapper.apply(voteRequest)), HttpStatus.OK);
    }

    @DeleteMapping("/vote/{id}")
    public  ResponseEntity<Void> supprimerVote(@PathVariable int id){
        this.voteService.supprimerVote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
