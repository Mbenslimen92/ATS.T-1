package de.tritux.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.entities.Skills;
@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer> {

}
