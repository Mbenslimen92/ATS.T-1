package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Services.CandidatureService;
import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Candidature;
import de.tritux.db.repositories.CandidatRepository;



@RestController
public class CandidatController {
	
	CandidatureService candidatureService;
	
	@Autowired
	private CandidatRepository candidatRepository;
	
	
	@GetMapping(value="/candidats")
	public List<Candidat> Candidats(){
		return candidatRepository.findAll();
		
	}
	@GetMapping(value="/candidats/{id}")
	public Candidat listCandidat(@PathVariable(name="id") Integer id){
		return candidatRepository.findById(id).get();
		
	}
	@PutMapping(value="/candidats/{id}")
	public Candidat update(@PathVariable(name="id") Integer id,@RequestBody Candidat c){
		c.setId(id);
		return candidatRepository.save(c);
		
	}
	@PostMapping(value="/candidats")
	public Candidat save(@RequestBody Candidat c){

		return candidatRepository.save(c);
	}
	@DeleteMapping(value="/candidats/{id}")
	public void delete(@PathVariable(name="id") Integer id){
		
		candidatRepository.deleteById(id);
	}
	
	
	
	
    }


