package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.Services.CandidatureService;
import de.tritux.db.entities.Candidature;

@RestController
public class CandidatureController {

    private CandidatureService candidatureService;

    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    @PostMapping("/candidatures/postuler")
    public ResponseEntity<Candidature> postulerOffreEmploi(@RequestParam Integer candidatId, @RequestParam Integer emploiId) {
        try {
            Candidature candidature = candidatureService.postulerOffreEmploi(candidatId, emploiId);
            return ResponseEntity.ok(candidature);
        } catch (NotFoundException e) {
            // Gérer l'exception lorsque le candidat ou l'offre d'emploi n'est pas trouvé
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Gérer les autres exceptions imprévues
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/candidatures/emploi/{emploiId}")
    public ResponseEntity<List<Candidature>> getCandidaturesByEmploiId(@PathVariable Integer emploiId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByEmploiId(emploiId);
        return ResponseEntity.ok(candidatures);
    }

    @DeleteMapping("/candidatures/{candidatureId}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable Integer candidatureId) {
        candidatureService.deleteCandidature(candidatureId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/candidatures/{candidatureId}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Integer candidatureId) {
        Candidature candidature = candidatureService.getCandidatureById(candidatureId);
        return ResponseEntity.ok(candidature);
    }

    @GetMapping("/candidatures/candidat/{candidatId}")
    public ResponseEntity<List<Candidature>> getCandidaturesByCandidatId(@PathVariable Integer candidatId) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByCandidatId(candidatId);
        return ResponseEntity.ok(candidatures);
    }

    @GetMapping("/candidatures")
    public ResponseEntity<List<Candidature>> getAllCandidatures() {
        List<Candidature> candidatures = candidatureService.getAllCandidatures();
        return ResponseEntity.ok(candidatures);
    }
}





