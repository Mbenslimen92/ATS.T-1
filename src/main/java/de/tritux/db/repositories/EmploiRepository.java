package de.tritux.db.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;
@Repository
public interface EmploiRepository extends JpaRepository<Emploi, Integer> {

	Emploi save(Recruteur e);
	List<Emploi> findByDateDePublication(String dateDePublication);
	List<Emploi> findAllByOrderByIdDesc();

	
}
