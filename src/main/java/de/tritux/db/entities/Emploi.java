package de.tritux.db.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Emploi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String Titre;
	public String description;
	public Date date_de_publication;
	public Emploi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emploi(Integer id, String titre, String description, Date date_de_publication) {
		super();
		this.id = id;
		Titre = titre;
		this.description = description;
		this.date_de_publication = date_de_publication;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate_de_publication() {
		return date_de_publication;
	}
	public void setDate_de_publication(Date date_de_publication) {
		this.date_de_publication = date_de_publication;
	}

	@ManyToOne
    @JoinColumn(name = "recruteur_id")
    private Recruteur recruteur;
	
	
	@OneToMany(mappedBy = "emploi")
    private Set<Candidature> candidatures = new HashSet<>();
	
	public void setRecruteur(Recruteur recruteur) {
		// TODO Auto-generated method stub
		
	}
	
	
}
