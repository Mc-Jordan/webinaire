package com.chilo_tech.gestion_webinaire.controllers;


import com.chilo_tech.gestion_webinaire.dto.BackendRequestVote;
import com.chilo_tech.gestion_webinaire.dto.FrontendRequestVote;
import com.chilo_tech.gestion_webinaire.restserver.IBackOfficeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/votes")
@CrossOrigin("*")
public class VoteController {
    private final IBackOfficeClient iBackOfficeClient;

    @Value("${backoffice.api.token}")
    private String TOKEN;

    public VoteController(IBackOfficeClient iBackOfficeClient) {
        this.iBackOfficeClient = iBackOfficeClient;
    }


    @PostMapping
    public ResponseEntity<?> ajouterVote(@RequestBody List<FrontendRequestVote> frontendRequestVotes) {
        List<BackendRequestVote> backendRequestVotes = frontendRequestVotes.stream().map(
                this::transformRequest
        ).toList();
        return iBackOfficeClient.createVotes("Bearer "+TOKEN, backendRequestVotes);
    }

    private BackendRequestVote transformRequest(FrontendRequestVote frontendRequest) {
        return new BackendRequestVote(
                frontendRequest.contacts().stream()
                        .map(BackendRequestVote.Contact::new)
                        .collect(Collectors.toList()),
                frontendRequest.questions().stream()
                        .map(BackendRequestVote.Question::new)
                        .collect(Collectors.toList()),
                frontendRequest.reponses().stream()
                        .map(BackendRequestVote.Reponse::new)
                        .collect(Collectors.toList())
        );
    }

/*
    private final VoteService voteService;
    private final VoteRequestMapper voteRequestMapper;

    @PostMapping
    public ResponseEntity<?> ajouterVote(@RequestBody VoteRequest voteRequest) {
        return new ResponseEntity<>(this.voteService.ajouterVote(this.voteRequestMapper.apply(voteRequest)), HttpStatus.CREATED);
    }


    @PostMapping("/all")
    public ResponseEntity<?> ajouterVote(@RequestBody List<VoteRequest> votesRequest) {
        return new ResponseEntity<>(this.voteService.ajouterVote(votesRequest.stream().map(this.voteRequestMapper).toList()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> afficheVote() {
        return new ResponseEntity<>(this.voteService.afficherVote(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/vote/{id}")
    public ResponseEntity<?> afficheVote(@PathVariable int id) {
        return new ResponseEntity<>(this.voteService.afficherVote(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/vote/{id}")
    public ResponseEntity<?> modifierVote(@PathVariable int id, @RequestBody VoteRequest voteRequest) {
        return new ResponseEntity<>(this.voteService.modifierVote(id, this.voteRequestMapper.apply(voteRequest)), HttpStatus.OK);
    }

    @DeleteMapping("/vote/{id}")
    public ResponseEntity<Void> supprimerVote(@PathVariable int id) {
        this.voteService.supprimerVote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 */
}
