package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import de.tritux.db.Services.RecruteurService;
import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;

import de.tritux.db.repositories.RecruteurRepository;


@RestController
@CrossOrigin("*")

public class RecruteurController {
	
	
	private final RecruteurService recruteurService;

    public RecruteurController(RecruteurService recruteurService, EmploiService emploiService) {
        this.recruteurService = recruteurService;
    }

    
	
	@Autowired
	private RecruteurRepository recruteurRepository;
	@GetMapping(value="/recruteurs")
	public List<Recruteur> Recruteurs(){
		return recruteurRepository.findAll();
		
	}
	@GetMapping(value="/recruteurs/{id}")
	public Recruteur listRecruteur(@PathVariable(name="id") Integer id){
		return recruteurRepository.findById(id).get();
		
	}
	@PutMapping(value="/recruteurs/{id}")
	public Recruteur update(@PathVariable(name="id") Integer id,@RequestBody Recruteur r){
		r.setId(id);
		return recruteurRepository.save(r);
		
	}
	@PostMapping(value="/recruteurs")
	public Recruteur save(@RequestBody Recruteur r){

		return recruteurRepository.save(r);
	}
	@DeleteMapping(value="/recruteurs/{id}")
	public void delete(@PathVariable(name="id") Integer id){
		
		recruteurRepository.deleteById(id);
	}
	public RecruteurService getRecruteurService() {
		return recruteurService;
	}
	
// Partie Emploi	
	
	
	
	@GetMapping("/offres")
    public List<Emploi> obtenirTousLesOffresEmploi() {
        List<Emploi> offresEmploi = recruteurService.obtenirTousLesOffresEmploi();
        System.out.println("NB OFFFFRE   "+offresEmploi.size());
        return offresEmploi;
    }
	
	 @GetMapping("/offres/{offreId}")
	    public ResponseEntity<Emploi> obtenirOffreEmploiParId(@PathVariable Integer offreId) {
	        Emploi emploi = recruteurService.obtenirOffreEmploiParId(offreId);
	        return ResponseEntity.ok(emploi);
	    }

	    @PostMapping("/offres")
	    public ResponseEntity<Emploi> ajouterOffreEmploi(@RequestBody Emploi emploi) {
	        Emploi nouvelleOffre = recruteurService.ajouterOffreEmploi(emploi);
	        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleOffre);
	    }

	    @PutMapping("/offres/{offreId}")
	    public ResponseEntity<Emploi> modifierOffreEmploiParId(@PathVariable Integer offreId, @RequestBody Emploi emploiModifie) {
	        Emploi emploiMisAJour = recruteurService.modifierOffreEmploiParId(offreId, emploiModifie);
	        return ResponseEntity.ok(emploiMisAJour);
	    }

	    @DeleteMapping("/offres/{offreId}")
	    public ResponseEntity<Void> supprimerOffreEmploiParId(@PathVariable Integer offreId) {
	        recruteurService.supprimerOffreEmploiParId(offreId);
	        return ResponseEntity.noContent().build();
	    }

	    
	    
	    

	}

