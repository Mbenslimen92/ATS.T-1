package de.tritux.db.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.OneToMany;

import de.tritux.db.authentication.UserAuthentication;
@Entity
@DiscriminatorValue("recruteur")
public class Recruteur extends User {
	
	
	private String matricule;
	
	private LocalDate DOB = LocalDate.of(2000, 11, 11);
	
	 private String role;
	
	
	
	
	public Recruteur(Integer id, String nom, String prenom, String mail, Long tel, String password, String matricule,
			LocalDate dOB, String role, Set<Emploi> emplois) {
		super(id, nom, prenom, mail, tel, password);
		this.matricule = matricule;
		DOB = dOB;
		this.setRole(role);
		this.emplois = emplois;
	}

	public Recruteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getNomcomplet() {
		return getnom()+ " " + getPrenom();
	}
	public int getAge() {
		return (int)ChronoUnit.YEARS.between(DOB, LocalDate.now());
		
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
		
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	
	@OneToMany(mappedBy = "recruteur")
    private Set<Emploi> emplois = new HashSet<>();
	
}
