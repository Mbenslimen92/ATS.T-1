package de.tritux.db.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;

public interface EmploiRepository extends JpaRepository<Emploi, Integer> {

	Emploi save(Recruteur e);

	
}
