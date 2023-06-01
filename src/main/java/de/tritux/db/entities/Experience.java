package de.tritux.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Experience(int id, Candidat candidat) {
		super();
		this.id = id;
		this.candidat = candidat;
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



	@ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

}