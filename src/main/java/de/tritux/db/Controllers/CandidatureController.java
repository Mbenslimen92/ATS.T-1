package de.tritux.db.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.Services.CandidatureService;
import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Candidature;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {

    private final CandidatureService candidatureService;

    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }
    @GetMapping("/candidatures")
    public ResponseEntity<List<Candidature>> getAllCandidatures() {
        List<Candidature> candidatures = candidatureService.getAllCandidatures();
        return ResponseEntity.ok(candidatures);
    }
    
    @GetMapping("/{candidatId}/candidatures")
    public ResponseEntity<List<Candidature>> getCandidaturesByCandidatId(@PathVariable Integer candidatId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByCandidatId(candidatId);
        return ResponseEntity.ok(candidatures);
    }

    @GetMapping("/{emploiId}/candidatures")
    public ResponseEntity<List<Candidature>> getCandidaturesByEmploiId(@PathVariable Integer emploiId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByEmploiId(emploiId);
        return ResponseEntity.ok(candidatures);
    }

    @GetMapping("/{candidatureId}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Integer candidatureId) {
        Candidature candidature = candidatureService.getCandidatureById(candidatureId);
        return ResponseEntity.ok(candidature);
    }

    

    @DeleteMapping("/{candidatureId}")
    public ResponseEntity<?> deleteCandidature(@PathVariable Integer candidatureId) {
        candidatureService.deleteCandidature(candidatureId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/postuler")
    public ResponseEntity<Candidature> postulerOffreEmploi(@RequestParam Integer candidatId, @RequestParam Integer emploiId) {
        Candidature candidature = candidatureService.postulerOffreEmploi(candidatId, emploiId);
        return ResponseEntity.ok(candidature);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Candidat>> searchCandidatesOnLinkedIn(@RequestParam String description) {
        List<Candidat> candidates = candidatureService.searchCandidatesOnLinkedIn(description);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/extract")
    public ResponseEntity<Candidat> extractCandidateInformation(@RequestParam String profileUrl) {
        try {
            Candidat candidat = candidatureService.extractCandidateInformation(profileUrl);
            return ResponseEntity.ok(candidat);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCandidates(@RequestBody List<Candidat> candidates, @RequestParam Integer emploiId) {
        try {
            candidatureService.saveCandidates(candidates, emploiId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Offre d'emploi introuvable");
        }
    }

}