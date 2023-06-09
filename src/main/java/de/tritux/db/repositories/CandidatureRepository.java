package de.tritux.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Candidature;
import de.tritux.db.entities.Emploi;
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

	boolean existsByCandidatAndEmploi(Candidat candidat, Emploi emploi);

	List<Candidature> findByEmploi(Emploi emploi);

	List<Candidature> findByCandidat(Candidat candidat);

}
