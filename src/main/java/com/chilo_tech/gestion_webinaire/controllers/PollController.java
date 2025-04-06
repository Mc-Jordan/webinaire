package com.chilo_tech.gestion_webinaire.controllers;


import com.chilo_tech.gestion_webinaire.model.Poll;
import com.chilo_tech.gestion_webinaire.services.pollService.PollService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls")
@AllArgsConstructor
public class PollController {
    private final PollService pollService;

    @PostMapping
    public ResponseEntity<?> ajouterPoll(@RequestBody Poll poll){
        return new ResponseEntity<>(this.pollService.ajouterPoll(poll), HttpStatus.CREATED);
    }
}
