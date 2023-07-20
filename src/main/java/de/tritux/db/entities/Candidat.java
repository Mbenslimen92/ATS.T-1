package de.tritux.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
@AllArgsConstructor

@Entity
public class Candidat extends User {

    private String resume; 
    private String universite; 
    private String currentJob; 
    private String profilLinkedIn;
    private String CandidatRole;
    private String Skills;
    // ...

   
    @JsonIgnore
    @OneToMany(mappedBy = "candidat")
    private Set<Candidature> candidatures = new HashSet<>();

    
    

    


    

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

    



	public Candidat orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
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

	public String getSkills() {
		return Skills;
	}

	public void setSkills(String skills) {
		Skills = skills;
	}
}
