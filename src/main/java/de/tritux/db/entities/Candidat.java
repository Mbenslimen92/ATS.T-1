package de.tritux.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("candidat")
public class Candidat extends User{


private String Resumé;
private String université;
private String CurrentJob;
private String role;






public Candidat(Integer id, String nom, String prenom, String mail, Long tel, String password, String resumé,
		String université, String currentJob, String role, Set<Candidature> candidatures, Set<Skills> skills,
		Set<Experience> experiences) {
	super(id, nom, prenom, mail, tel, password);
	Resumé = resumé;
	this.université = université;
	CurrentJob = currentJob;
	this.role = role;
	this.candidatures = candidatures;
	this.skills = skills;
	this.experiences = experiences;
}


public Candidat() {
	super();
	// TODO Auto-generated constructor stub
}


public String getResumé() {
	return Resumé;
}
public void setResumé(String resumé) {
	Resumé = resumé;
}
public String getUniversité() {
	return université;
}
public void setUniversité(String université) {
	this.université = université;
}
public String getCurrentJob() {
	return CurrentJob;
}
public void setCurrentJob(String currentJob) {
	CurrentJob = currentJob;
}

public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}


public void login(String password) {
    if (authenticate(password)) {
        // Authentification réussie, effectuer les actions spécifiques au Candidat
        System.out.println("Connexion réussie en tant que candidat.");
        System.out.println("Compétences : " + skills);
        System.out.println("Rôle : " + role);
    } else {
        // Authentification échouée, gérer l'erreur
        System.out.println("Échec de la connexion en tant que candidat. Veuillez vérifier vos informations d'identification.");
    }
}



@OneToMany(mappedBy = "candidat")
private Set<Candidature> candidatures = new HashSet<>();

@OneToMany(mappedBy = "candidat")
private Set<Skills> skills = new HashSet<>();

@OneToMany(mappedBy = "candidat")
private Set<Experience> experiences = new HashSet<>();
}
