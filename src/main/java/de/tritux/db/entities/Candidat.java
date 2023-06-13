package de.tritux.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("candidat")
public class Candidat extends User {

    private String resume; // Résumé du profil du candidat
    private String universite; // Université fréquentée par le candidat
    private String currentJob; // Poste actuel du candidat
    private String profilLinkedIn;
    private String role; // Rôle ou fonction du candidat

    


    public Candidat(Integer id, String nom, String prenom, String mail, Long tel, String password, String resume,
			String universite, String currentJob, String profilLinkedIn, String role, Set<Candidature> candidatures,
			Set<Skills> skills, Set<Experience> experiences) {
		super(id, nom, prenom, mail, tel, password);
		this.resume = resume;
		this.universite = universite;
		this.currentJob = currentJob;
		this.profilLinkedIn = profilLinkedIn;
		this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
