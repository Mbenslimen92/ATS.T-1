package de.tritux.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.entities.Experience;
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

}
