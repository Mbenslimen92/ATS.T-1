package de.tritux.db.entities;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("recruteur")
public class Recruteur extends User {
	
	
	private String matricule;
	
	private LocalDate DOB = LocalDate.of(2000, 11, 11);
	
	 private String RecruteurtRole;
	
	
	
	
	
	public Recruteur(Integer id, String nom, String prenom, String mail, Long tel, String password, String matricule,
			LocalDate dOB, String recruteurtRole, Set<Emploi> emplois) {
		super(id, nom, prenom, mail, tel, password);
		this.matricule = matricule;
		setDOB(dOB);
		RecruteurtRole = recruteurtRole;
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
	
	
	
	

	
	public String getRecruteurtRole() {
		return RecruteurtRole;
	}


	public void setRecruteurtRole(String recruteurtRole) {
		RecruteurtRole = recruteurtRole;
	}






	public LocalDate getDOB() {
		return DOB;
	}


	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}






	@OneToMany(mappedBy = "recruteur")
    private Set<Emploi> emplois = new HashSet<>();
	
}
