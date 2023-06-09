package de.tritux.db.repositories;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Recruteur;

public interface CandidatRepository extends JpaRepository<Candidat,Integer> {

	Candidat save(Recruteur recruteur);

	List<Candidat> findByResumeContaining(String resume);



}
