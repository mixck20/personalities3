package com.deloria.personality;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/deloria/personalities")
@CrossOrigin(origins = "http://localhost:5173")
public class PersonalityController {

    private final PersonalityRepository personalityRepository;

    public PersonalityController(PersonalityRepository personalityRepository) {
        this.personalityRepository = personalityRepository;
    }

    @GetMapping
    public ResponseEntity<List<Personality>> getAllPersonalities() {
        List<Personality> personalities = (List<Personality>) personalityRepository.findAll();
        return new ResponseEntity<>(personalities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPersonality(@RequestBody Personality personality) throws URISyntaxException {
        Personality saved = personalityRepository.save(personality);
        return ResponseEntity.created(new URI("/deloria/personalities/" + saved.getId())).body(saved);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createBulkPersonalities(@RequestBody List<Personality> personalities) {
        try {
            if (personalities == null || personalities.isEmpty()) {
                return ResponseEntity.badRequest().body("The request body cannot be empty.");
            }
            Iterable<Personality> saved = personalityRepository.saveAll(personalities);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving data: " + e.getMessage());
        }
    }

}