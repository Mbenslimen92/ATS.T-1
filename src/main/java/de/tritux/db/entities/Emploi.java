package de.tritux.db.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
    private Integer id;
    
    private String titre;
    @Column(name = "description")
    private String description;
    private Date dateDePublication;
   
    
    
    @Column(name = "src")
    private String src;
    
    // Constructeurs, getters et setters

    public Emploi() {
        super();
    }

    public Emploi(Integer id, String titre, String description, Date dateDePublication) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateDePublication = dateDePublication;
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
}
