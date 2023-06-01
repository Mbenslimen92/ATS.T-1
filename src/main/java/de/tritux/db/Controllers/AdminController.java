package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import de.tritux.db.entities.Admin;


import de.tritux.db.repositories.AdminRepository;



@RestController
public class AdminController {
	
	

	
	
	private AdminRepository adminRepository;
	@GetMapping(value="/admins")
	public List<Admin> Admins(){
		return adminRepository.findAll();
		
	}
	@GetMapping(value="/admins/{id}")
	public Admin listAdmin(@PathVariable(name="id") Integer id){
		return adminRepository.findById(id).get();
		
	}
	@PutMapping(value="/admins/{id}")
	public Admin update(@PathVariable(name="id") Integer id,@RequestBody Admin a){
		a.setId(id);
		return adminRepository.save(a);
		
	}
	@PostMapping(value="/admins")
	public Admin save(@RequestBody Admin a){

		return adminRepository.save(a);
	}
	@DeleteMapping(value="/admins/{id}")
	public void delete(@PathVariable(name="id") Integer id){
		
		adminRepository.deleteById(id);
	}
	

}
