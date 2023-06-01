package de.tritux.db.entities;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

	
	private Integer id;
	

private String nom;
private String prenom;
private String mail;
private Long tel;
private String password;
	
	
	
	
	public User(Integer id, String nom, String prenom, String mail, Long tel ,String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer Id) {
		this.id = Id;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Long getTel() {
		return tel;
	}
	public void setTel(Long tel) {
		this.tel = tel;
	}
	public String getnom() {
		return nom;
	}
	public void setnom(String nom) {
		this.nom = nom;
	}
	

	@ManyToOne
	@JoinColumn(name="admin_id")
		  private Admin admin;




	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPassword(String encodedPassword) {
		// TODO Auto-generated method stub
		
	}
	   
	     
	
	public boolean authenticate(String password) {
          return this.password.equals(password);
    }
    
	
	
	    }
	



	
	




	
	
