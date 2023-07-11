package de.tritux.db.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}
