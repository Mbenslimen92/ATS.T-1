package de.tritux.db.Controllers;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Services.EmploiService;
import de.tritux.db.entities.Emploi;

@RestController
@CrossOrigin("*")
public class EmploiController {
private  EmploiService emploiService ;
@GetMapping("/offres")
public List<Emploi> obtenirTousLesOffresEmploi() {
    List<Emploi> offresEmploi = emploiService.obtenirTousLesOffresEmploi();
    System.out.println("NB OFFFFRE   "+offresEmploi.size());
    return offresEmploi;
}

 @GetMapping("/offres/{offreId}")
    public ResponseEntity<Emploi> obtenirOffreEmploiParId(@PathVariable Integer offreId) {
        Emploi emploi = emploiService.obtenirOffreEmploiParId(offreId);
        return ResponseEntity.ok(emploi);
    }

    @PostMapping("/offres")
    public ResponseEntity<Emploi> ajouterOffreEmploi(@RequestBody Emploi emploi) {
        Emploi nouvelleOffre = emploiService.ajouterOffreEmploi(emploi);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleOffre);
    }

    @PutMapping("/offres/{offreId}")
    public ResponseEntity<Emploi> modifierOffreEmploiParId(@PathVariable Integer offreId, @RequestBody Emploi emploiModifie) {
        Emploi emploiMisAJour = emploiService.modifierOffreEmploiParId(offreId, emploiModifie);
        return ResponseEntity.ok(emploiMisAJour);
    }

    @DeleteMapping("/offres/{offreId}")
    public ResponseEntity<Void> supprimerOffreEmploiParId(@PathVariable Integer offreId) {
    	emploiService.supprimerOffreEmploiParId(offreId);
        return ResponseEntity.noContent().build();
    }
    
   

    }

    
    


