package de.tritux.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Candidat extends User {

    private String resume; // Résumé du profil du candidat
    private String universite; // Université fréquentée par le candidat
    private String currentJob; // Poste actuel du candidat
    private String profilLinkedIn;
    private String CandidatRole;
    
    // ...

   

    


    public Candidat(Integer candidatId, String nom, String prenom, String mail, Long tel, String password, String resume,
			String universite, String currentJob, String profilLinkedIn,Set<Candidature> candidatures,
			Set<Skills> skills, Set<Experience> experiences) {
		super(candidatId, nom, prenom, mail, tel, password);
		this.resume = resume;
		this.universite = universite;
		this.currentJob = currentJob;
		this.profilLinkedIn = profilLinkedIn;
		this.candidatures = candidatures;
		this.skills = skills;
		this.experiences = experiences;
	}

	public Candidat() {
        super();
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }


    public Set<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(Set<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "candidat")
    private Set<Candidature> candidatures = new HashSet<>();

    @OneToMany(mappedBy = "candidat")
    private Set<Skills> skills = new HashSet<>();

    @OneToMany(mappedBy = "candidat")
    private Set<Experience> experiences = new HashSet<>();



	public Candidat orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProfilLinkedIn() {
		return profilLinkedIn;
	}

	public void setProfilLinkedIn(String profilLinkedIn) {
		this.profilLinkedIn = profilLinkedIn;
	}

	public void setExperiences(String experience) {
		// TODO Auto-generated method stub
		
	}

	public void setCompetences(String competences) {
		// TODO Auto-generated method stub
		
	}

	public void setFormations(String formations) {
		// TODO Auto-generated method stub
		
	}

	public String getCandidatRole() {
		return CandidatRole;
	}

	public void setCandidatRole(String candidatRole) {
		CandidatRole = candidatRole;
	}
}
