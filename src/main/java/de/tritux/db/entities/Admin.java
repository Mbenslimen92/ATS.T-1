package de.tritux.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "admin")
public class Admin extends User {
    
    private String adminRole;
    
    // ...

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

  


		
		
		@OneToMany(mappedBy = "admin")
		private Set<User> users = new HashSet<>();
	}



