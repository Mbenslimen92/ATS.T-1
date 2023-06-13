package de.tritux.db.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import de.tritux.db.MotCle.MotCle;

@Entity
public class Emploi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
    
     String titre;
    @Column(name = "description")
     String description;
     Date dateDePublication;
     private String motsCles;
     @Column(name = "src")
    private String src;
    

    public Emploi() {
        super();
    }

    
    public Emploi(Integer id, String titre, String description, Date dateDePublication,
			String motsCles, String src, Recruteur recruteur, Set<Candidature> candidatures) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateDePublication = dateDePublication;
		this.setMotsCles(motsCles);
		this.src = src;
		this.recruteur = recruteur;
		this.candidatures = candidatures;
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    
	
	@ManyToOne
    @JoinColumn(name = "recruteur_id")
    private Recruteur recruteur;
    
    @OneToMany(mappedBy = "emploi")
    private Set<Candidature> candidatures = new HashSet<>();
    
    
    @ManyToMany
    @JoinTable(name = "emploi_motcle",
        joinColumns = @JoinColumn(name = "emploi_id"),
        inverseJoinColumns = @JoinColumn(name = "motcle_id"))
    private List<MotCle> motsCles1;


	public void setMotsCles(List<String> motCles) {
		// TODO Auto-generated method stub
		
	}


	public String getMotsCles() {
		return motsCles;
	}


	public void setMotsCles(String motsCles) {
		this.motsCles = motsCles;
	}
}
