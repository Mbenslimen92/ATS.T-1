package de.tritux.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tritux.db.MotCle.MotCle;
@Repository
public interface MotCleRepository extends JpaRepository<MotCle, Integer>{

	MotCle findByValeurMC(String motCleValeur);

}
