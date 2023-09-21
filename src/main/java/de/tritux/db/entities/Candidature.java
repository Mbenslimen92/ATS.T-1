package de.tritux.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "candidature")
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom_candidat")
    private String nomCandidat;
    @Column(name = "prenom_candidat")
    private String prenomCandidat;

    @Column(name = "email_candidat")
    private String emailCandidat;


    public Candidature(int id, Candidat candidat, Emploi emploi) {
		super();
		this.id = id;
		this.candidat = candidat;
		this.emploi = emploi;
	}

	public Candidature() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public Emploi getEmploi() {
		return emploi;
	}

	public void setEmploi(Emploi emploi) {
		this.emploi = emploi;
	}



	@ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "emploi_id")
    private Emploi emploi;



	public void setTitrePoste(String titrePoste) {
		// TODO Auto-generated method stub
		
	}
}
