package com.chilo_tech.gestion_webinaire.controllers;

import com.chilo_tech.gestion_webinaire.dto.OptionRequest;
import com.chilo_tech.gestion_webinaire.mapper.OptionRequestMapper;
import com.chilo_tech.gestion_webinaire.services.optionService.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
@AllArgsConstructor
public class OptionController {

    private final OptionRequestMapper optionRequestMapper;
    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<?> ajouterOption(@RequestBody OptionRequest optionRequest) {
        return new ResponseEntity<>(this.optionService.ajouterOption(this.optionRequestMapper.apply(optionRequest)), HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<?> ajouterOption(@RequestBody List<OptionRequest> optionsRequest) {
        return new ResponseEntity<>(this.optionService.ajouterOption(optionsRequest.stream().map(this.optionRequestMapper).toList()), HttpStatus.CREATED);
    }

    @PutMapping("/option/{id}")
    public ResponseEntity<?> modifierOption(@PathVariable int id, @RequestBody OptionRequest optionRequest) {
        return new ResponseEntity<>(this.optionService.modifierOption(id, this.optionRequestMapper.apply(optionRequest)), HttpStatus.OK);
    }

    @GetMapping("/option/{id}")
    public ResponseEntity<?> afficherOption(@PathVariable int id) {
        return new ResponseEntity<>(this.optionService.afficherOption(id), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> afficherOption() {
        return new ResponseEntity<>(this.optionService.afficherOption(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/option/{id}")
    public ResponseEntity<?> supprimerOption(@PathVariable int id) {
        this.optionService.supprimerOption(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
