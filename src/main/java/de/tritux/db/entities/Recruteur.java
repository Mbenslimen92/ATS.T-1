package de.tritux.db.entities;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import javax.persistence.OneToMany;

@Entity
public class Recruteur extends User {
	
	
	private String matricule;
	
	
	
	
	
	
	
	public Recruteur(Integer id, String nom, String prenom, String mail, Long tel, String password, String matricule,
			 Set<Emploi> emplois) {
		super(id, nom, prenom, mail, tel, password);
		this.matricule = matricule;
		this.emplois = emplois;
	}


	public Recruteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
		
	}
	
	
	
	

	






	






	@OneToMany(mappedBy = "recruteur")
    private Set<Emploi> emplois = new HashSet<>();
	
}
