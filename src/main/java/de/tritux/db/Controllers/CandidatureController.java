package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.Services.CandidatureService;
import de.tritux.db.entities.Candidature;
import de.tritux.db.entities.Emploi;
import de.tritux.db.models.PostulerRequest;
import de.tritux.db.repositories.EmploiRepository;

@RestController
public class CandidatureController {

	 private final CandidatureService candidatureService;
	    private final EmploiRepository emploiRepository; 

	    public CandidatureController(CandidatureService candidatureService, EmploiRepository emploiRepository) {
	        this.candidatureService = candidatureService;
	        this.emploiRepository = emploiRepository; 
	    }
	    
	    
	    
	    @PostMapping("/candidatures/postuler")
	    public ResponseEntity<String> postulerOffreEmploi(@RequestBody PostulerRequest request) {
	        try {
	            candidatureService.postulerOffreEmploi(request.getCandidatId(), request.getEmploiId());
	            return ResponseEntity.ok("Candidature effectuée avec succès.");
	        } catch (NotFoundException e) {
	            return ResponseEntity.notFound().build();
	        } catch (IllegalStateException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        } catch (Exception e) {
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
    

    @PostMapping("/offres-emploi/{emploiId}/scraping-linkedin")
    public ResponseEntity<String> scraperLinkedInPourProfiles(@PathVariable Integer emploiId) {
        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

        candidatureService.scraperLinkedInPourProfiles(emploi);

        return ResponseEntity.ok("Scraping des profils LinkedIn effectué avec succès.");
    }
    
    
}





