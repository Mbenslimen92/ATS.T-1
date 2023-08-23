package de.tritux.db.repositories;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Recruteur;
@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Integer> {

	Candidat save(Recruteur recruteur);




}
