package de.tritux.db.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByMail(String mail);
    Boolean existsByNom(String nom);
}
