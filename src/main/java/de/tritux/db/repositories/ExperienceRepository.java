package de.tritux.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tritux.db.entities.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

}
