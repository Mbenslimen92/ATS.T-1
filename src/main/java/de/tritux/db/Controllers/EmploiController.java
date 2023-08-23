package de.tritux.db.Controllers;


import java.util.List;

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
import de.tritux.db.models.EmploiDto;
import de.tritux.db.models.UserDto;

@RestController
@CrossOrigin("*")
public class EmploiController {
    private final EmploiService emploiService;

    public EmploiController(EmploiService emploiService) {
        this.emploiService = emploiService;
    }
    
    
@GetMapping("/emplois")
public ResponseEntity<List<EmploiDto>> getAllEmploi() {
	List<EmploiDto> emplois = emploiService.getAllEmploi();
	return ResponseEntity.ok(emplois);
}

@GetMapping("/emplois/{emploiId}")
public Emploi obtenirOffreEmploiParId(@PathVariable Integer emploiId) {
	
	Emploi emploi = emploiService.obtenirOffreEmploiParId(emploiId);
  /*  if (emploi == null) {
        return ResponseEntity.notFound().build();
    }*/
    System.out.println("Id ***** "+emploiId+" Date pub "+emploi.getDateDePublication());
    return emploi;
}


    @PostMapping("/emplois")
    public ResponseEntity<Emploi> ajouterOffreEmploi(@RequestBody Emploi emploi) {
        Emploi nouvelleOffre = emploiService.ajouterOffreEmploi(emploi);
       return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleOffre);
      
    }

    @PutMapping("/emplois/{emploiId}")
    public ResponseEntity<Emploi> modifierOffreEmploiParId(@PathVariable Integer emploiId, @RequestBody Emploi emploiModifie) {
        Emploi emploiMisAJour = emploiService.modifierOffreEmploiParId(emploiId, emploiModifie);
        return ResponseEntity.ok(emploiMisAJour);
    }


    @DeleteMapping("/emplois/{emploiId}")
    public ResponseEntity<Void> supprimerOffreEmploiParId(@PathVariable Integer emploiId) {
        emploiService.supprimerOffreEmploiParId(emploiId);
        return ResponseEntity.noContent().build();
    }

    
   

    }

    
    


