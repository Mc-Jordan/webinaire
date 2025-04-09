package com.chilo_tech.gestion_webinaire.controllers;


import com.chilo_tech.gestion_webinaire.dto.PollRequest;
import com.chilo_tech.gestion_webinaire.mapper.PollRequestMapper;
import com.chilo_tech.gestion_webinaire.services.pollService.PollService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls")
@AllArgsConstructor
public class PollController {
    private final PollService pollService;
    private final PollRequestMapper pollRequestMapper;

    @PostMapping
    public ResponseEntity<?> ajouterPoll(@RequestBody PollRequest pollRequest) {
        return new ResponseEntity<>(this.pollService.ajouterPoll(this.pollRequestMapper.apply(pollRequest)), HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<?> ajouterPoll(@RequestBody List<PollRequest> pollRequests) {
        return new ResponseEntity<>(this.pollService.ajouterPoll(pollRequests.stream().map(this.pollRequestMapper).toList()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> afficherPoll() {
        return new ResponseEntity<>(this.pollService.afficherPoll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/poll/{id}")
    public ResponseEntity<?> afficherPoll(@PathVariable int id) {
        return new ResponseEntity<>(this.pollService.afficherPoll(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/poll/{id}")
    public ResponseEntity<?> modifierPoll(@PathVariable int id, @RequestBody PollRequest pollRequest) {
        return new ResponseEntity<>(this.pollService.modifierPoll(this.pollRequestMapper.apply(pollRequest), id), HttpStatus.OK);
    }

    @DeleteMapping("/poll/{id}")
    public ResponseEntity<?> supprimerPoll(int id) {
        this.pollService.supprimerPoll(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
