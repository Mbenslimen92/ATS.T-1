package de.tritux.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import de.tritux.db.authentication.UserAuthentication;

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

		
		
		@OneToMany(mappedBy = "admin")
		private Set<User> users = new HashSet<>();
	}



