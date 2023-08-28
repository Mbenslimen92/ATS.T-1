package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Services.EmploiService;
import de.tritux.db.Services.RecruteurService;
import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;

import de.tritux.db.repositories.RecruteurRepository;





@RequestMapping("/recruteurs")
@RestController
@CrossOrigin("*")
public class RecruteurController {
	
    private final RecruteurService recruteurService;

    public RecruteurController(RecruteurService recruteurService) {
        this.recruteurService = recruteurService;
    }

    // Get all Recruteurs
    @GetMapping
    @PreAuthorize("hasAuthority('recruteur:read')")
    public List<Recruteur> getAllRecruteurs() {
        return recruteurService.getAllRecruteur();
    }

    // Create a new Recruteur
    @PostMapping
    @PreAuthorize("hasAuthority('recruteur:create')")
    public Recruteur createRecruteur(@RequestBody Recruteur recruteur) {
        return recruteurService.saveRecruteur(recruteur);
    }

    // Get a single Recruteur
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('recruteur:read')")
    public Recruteur getRecruteurById(@PathVariable Integer id) {
        return recruteurService.getOne(id);
    }

    // Update a Recruteur
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('recruteur:update')")
    public Recruteur updateRecruteur(@PathVariable Integer id, @RequestBody Recruteur recruteurDetails) {
        Recruteur recruteur = recruteurService.getOne(id);
        recruteur.setNom(recruteurDetails.getNom());
        recruteur.setPrenom(recruteurDetails.getPrenom());
        // add more fields here to update
        return recruteurService.updateRecruteur(recruteur);
    }

    // Delete a Recruteur
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('recruteur:delete')")
    public ResponseEntity<?> deleteRecruteur(@PathVariable Integer id) {
        recruteurService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
