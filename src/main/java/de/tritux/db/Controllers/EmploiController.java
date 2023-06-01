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


import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;
import de.tritux.db.repositories.EmploiRepository;


@RestController
public class EmploiController {
	@Autowired
	private EmploiRepository emploiRepository;
	@GetMapping(value="/emplois")
	public List<Emploi> Emplois(){
		return emploiRepository.findAll();
		
	}
	@GetMapping(value="/emplois/{id}")
	public Emploi listEmploi(@PathVariable(name="id") Integer id){
		return emploiRepository.findById(id).get();
		
	}
	@PutMapping(value="/emplois/{id}")
	public Emploi update(@PathVariable(name="id") Integer id,@RequestBody Recruteur e){
		e.setId(id);
		return emploiRepository.save(e);
		
	}
	@PostMapping(value="/emplois")
	public Emploi save(@RequestBody Recruteur e){

		return emploiRepository.save(e);
	}
	@DeleteMapping(value="/emplois/{id}")
	public void delete(@PathVariable(name="id") Integer id){
		
		emploiRepository.deleteById(id);
	}}
