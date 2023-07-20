package de.tritux.db.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@Entity
public class Emploi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titre;

    
    private String description;

    private Date dateDePublication;

    private String motsCles;

    private String image;

    private String natureTravail;

    private String education;

    private String experience;

    
    @ManyToOne
    @JoinColumn(name = "recruteur_id")
    private Recruteur recruteur;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "emploi", fetch = FetchType.LAZY)
    private Set<Candidature> candidatures = new HashSet<>();
    
    
    

    public Emploi() {
        super();
    }

    public Emploi(Integer id, String titre, String description, Date dateDePublication, String motsCles, String image,
			String natureTravail, String education, String experience, Recruteur recruteur,
			Set<Candidature> candidatures) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateDePublication = dateDePublication;
		this.motsCles = motsCles;
		this.image = image;
		this.natureTravail = natureTravail;
		this.education = education;
		this.experience = experience;
		this.recruteur = recruteur;
		this.candidatures = candidatures;
	}



    


	public String getImage() {
		return image;
	}





	public void setImage(String image) {
		this.image = image;
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





	public String getExperience() {
		return experience;
	}





	public void setExperience(String experience) {
		this.experience = experience;
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

    public Date getDateDePublication() {
        return dateDePublication;
    }

    public void setDateDePublication(Date dateDePublication) {
        this.dateDePublication = dateDePublication;
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

   
    
	
	

	public void setMotsCles(List<String> motCles) {
		// TODO Auto-generated method stub
		
	}


	public String getMotsCles() {
		return motsCles;
	}


	public void setMotsCles(String motsCles) {
		this.motsCles = motsCles;
	}}