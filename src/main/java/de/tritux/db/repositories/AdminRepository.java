package de.tritux.db.repositories;



import org.springframework.data.jpa.repository.JpaRepository;


import de.tritux.db.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {


}
