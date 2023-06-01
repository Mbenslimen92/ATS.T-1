package de.tritux.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tritux.db.entities.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {

}
