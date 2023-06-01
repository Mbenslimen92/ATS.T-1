package de.tritux.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("admin")
	public class Admin extends User {
		
	 private String role;

		
		



		

		public Admin(Integer id, String nom, String prenom, String mail, Long tel, String password, String role,
			Set<User> users) {
		super(id, nom, prenom, mail, tel, password);
		this.role = role;
		this.users = users;
	}


		public Admin() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		public Set<User> getUsers() {
			return users;
		}

		public void setUsers(Set<User> users) {
			this.users = users;
		}

		
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		
		
	    public void login(String password) {
	        if (authenticate(password)) {
	            
	            System.out.println("Connexion réussie en tant qu'administrateur.");
	            System.out.println("Rôle : " + role);
	        } else {
	    
	            System.out.println("Échec de la connexion en tant qu'administrateur. Veuillez vérifier vos informations d'identification.");
	        }
	    }
		
		@OneToMany(mappedBy = "admin")
		private Set<User> users = new HashSet<>();
	}



