package de.tritux.db.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import de.tritux.db.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByNom(String nom);
    User findByMail(String mail);

}
