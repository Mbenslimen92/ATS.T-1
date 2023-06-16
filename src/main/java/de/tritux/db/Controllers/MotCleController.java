package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.MotCle.MotCle;
import de.tritux.db.Services.MotCleService;

@RestController
@RequestMapping("/motscles")
public class MotCleController {

    private final MotCleService motCleService;

    public MotCleController(MotCleService motCleService) {
        this.motCleService = motCleService;
    }

    @PostMapping("/{emploiId}")
    public ResponseEntity<MotCle> createMotCleWithEmploiId(@PathVariable Integer emploiId, @RequestBody String valeurMC) {
        MotCle createdMotCle = motCleService.createMotCleWithEmploiId(emploiId, valeurMC);
        return ResponseEntity.ok(createdMotCle);
    }

    @GetMapping
    public ResponseEntity<List<MotCle>> getAllMotsCles() {
        List<MotCle> motsCles = motCleService.getAllMotsCles();
        return ResponseEntity.ok(motsCles);
    }

    @GetMapping("/{motCleId}")
    public ResponseEntity<MotCle> getMotCleById(@PathVariable Integer motCleId) {
        MotCle motCle = motCleService.getMotCleById(motCleId);
        return ResponseEntity.ok(motCle);
    }

    @DeleteMapping("/{motCleId}")
    public ResponseEntity<Void> deleteMotCle(@PathVariable Integer motCleId) {
        motCleService.deleteMotCle(motCleId);
        return ResponseEntity.noContent().build();
    }
}
