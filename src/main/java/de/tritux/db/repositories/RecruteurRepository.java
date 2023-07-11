package de.tritux.db.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.entities.Recruteur;
@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Integer> {

}
