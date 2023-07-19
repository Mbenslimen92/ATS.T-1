package de.tritux.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


	@ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;


    public Skills(Integer id, Candidat candidat) {
		super();
		this.id = id;
		this.candidat = candidat;
	}



	public Skills() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Candidat getCandidat() {
		return candidat;
	}



	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}



}