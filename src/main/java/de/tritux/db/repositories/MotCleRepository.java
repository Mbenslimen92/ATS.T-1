package de.tritux.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tritux.db.MotCle.MotCle;

public interface MotCleRepository extends JpaRepository<MotCle, Integer>{

	MotCle findByValeurMC(String motCleValeur);

}
