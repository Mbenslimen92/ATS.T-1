package de.tritux.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import de.tritux.db.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByMail(String mail);
	



}
