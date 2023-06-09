package de.tritux.db.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Services.CandidatureService;
import de.tritux.db.entities.Candidature;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {

    private CandidatureService candidatureService;

    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }
    
    

    @PostMapping("/{candidatId}/{emploiId}")
    public ResponseEntity<Candidature> postulerOffreEmploi(@PathVariable Integer candidatId, @PathVariable Integer emploiId) {
        Candidature candidature = candidatureService.postulerOffreEmploi(candidatId, emploiId);
        return ResponseEntity.ok(candidature);
    }

    @PostMapping("/linkedin/{emploiId}")
    public ResponseEntity<Void> processLinkedInCandidature(@RequestParam String src, @PathVariable Integer emploiId) throws IOException {
        candidatureService.processLinkedInCandidature(src, emploiId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/candidatures")
    public List<Candidature> getAllCandidatures() {
        return candidatureService.getAllCandidatures();
    }
    
    
    @GetMapping("/candidatures/{candidatureId}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Integer candidatureId) {
        Candidature candidature = candidatureService.getCandidatureById(candidatureId);
        return ResponseEntity.ok(candidature);
    }

    @GetMapping("/candidats/{candidatId}")
    public ResponseEntity<List<Candidature>> getCandidaturesByCandidatId(@PathVariable Integer candidatId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByCandidatId(candidatId);
        return ResponseEntity.ok(candidatures);
    }

    @GetMapping("/emplois/{emploiId}")
    public ResponseEntity<List<Candidature>> getCandidaturesByEmploiId(@PathVariable Integer emploiId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByEmploiId(emploiId);
        return ResponseEntity.ok(candidatures);
    }

    @DeleteMapping("/{candidatureId}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable Integer candidatureId) {
        candidatureService.deleteCandidature(candidatureId);
        return ResponseEntity.ok().build();
    }
}
