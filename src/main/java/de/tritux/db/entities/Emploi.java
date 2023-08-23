package de.tritux.db.entities;


import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Emploi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titre;
    private String description;
    private String mission;
    private String ExigencesDeLemploi;

    private String dateDePublication;
    private String motsCles;
    private String natureTravail;
    private String education;
    private String competence;
    private String experience;
    private String localisation;
    private String postes_vacants;
    private String type_emploi;
    private String genre;
    private String date_expiration;
    
    @ManyToOne
    @JoinColumn(name = "recruteur_id")
    private Recruteur recruteur;
    
    
    
    @OneToMany(mappedBy = "emploi", fetch = FetchType.LAZY)
    @JsonIgnore

    private Set<Candidature> candidatures = new HashSet<>();


	
	


	

	public Emploi(Integer id, String titre, String description, String mission, String exigencesDeLemploi,
			String dateDePublication, String motsCles, String natureTravail, String education, String competence,
			String experience, String localisation, String postes_vacants, String type_emploi, String genre,
			String date_expiration, Recruteur recruteur, Set<Candidature> candidatures) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.mission = mission;
		ExigencesDeLemploi = exigencesDeLemploi;
		this.dateDePublication = dateDePublication;
		this.motsCles = motsCles;
		this.natureTravail = natureTravail;
		this.education = education;
		this.competence = competence;
		this.experience = experience;
		this.localisation = localisation;
		this.postes_vacants = postes_vacants;
		this.type_emploi = type_emploi;
		this.genre = genre;
		this.date_expiration = date_expiration;
		this.recruteur = recruteur;
		this.candidatures = candidatures;
	}


	public Emploi() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDateDePublication() {
		return dateDePublication;
	}


	public void setDateDePublication(String dateDePublication) {
		this.dateDePublication = dateDePublication;
	}


	public String getMotsCles() {
		return motsCles;
	}


	public void setMotsCles(String motsCles) {
		this.motsCles = motsCles;
	}


	


	public String getNatureTravail() {
		return natureTravail;
	}


	public void setNatureTravail(String natureTravail) {
		this.natureTravail = natureTravail;
	}


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	public String getCompetence() {
		return competence;
	}


	public void setCompetence(String competence) {
		this.competence = competence;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}


	public String getLocalisation() {
		return localisation;
	}


	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}


	public String getPostes_vacants() {
		return postes_vacants;
	}


	public void setPostes_vacants(String postes_vacants) {
		this.postes_vacants = postes_vacants;
	}


	public String getType_emploi() {
		return type_emploi;
	}


	public void setType_emploi(String type_emploi) {
		this.type_emploi = type_emploi;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getDate_expiration() {
		return date_expiration;
	}


	public void setDate_expiration(String date_expiration) {
		this.date_expiration = date_expiration;
	}


	public Recruteur getRecruteur() {
		return recruteur;
	}


	public void setRecruteur(Recruteur recruteur) {
		this.recruteur = recruteur;
	}


	public Set<Candidature> getCandidatures() {
		return candidatures;
	}


	public void setCandidatures(Set<Candidature> candidatures) {
		this.candidatures = candidatures;
	}


	public String getMission() {
		return mission;
	}


	public void setMission(String mission) {
		this.mission = mission;
	}


	public String getExigencesDeLemploi() {
		return ExigencesDeLemploi;
	}


	public void setExigencesDeLemploi(String exigencesDeLemploi) {
		ExigencesDeLemploi = exigencesDeLemploi;
	}
    
	
	
}