package de.tritux.db.Controllers;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.tritux.db.Services.CandidatService;
import de.tritux.db.entities.Candidat;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidats")
@CrossOrigin("*")
public class CandidatController {

    private final CandidatService candidatService;

    @Autowired
    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    @GetMapping
    public ResponseEntity<List<Candidat>> getAllCandidats() {
        List<Candidat> candidats = candidatService.getAllCandidat();
        if (candidats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(candidats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable Integer id) {
        Candidat candidat = candidatService.getOne(id);
        return candidat != null ? ResponseEntity.ok(candidat) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidatService.saveCandidat(candidat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable Integer id, @RequestBody Candidat candidat) {
        if (candidatService.getOne(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(candidatService.updateCandidat(candidat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable Integer id) {
        if (candidatService.getOne(id) == null) {
            return ResponseEntity.notFound().build();
        }
        candidatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
    }



