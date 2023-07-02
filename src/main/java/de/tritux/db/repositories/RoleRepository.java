package de.tritux.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tritux.db.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findById(String string);

}
