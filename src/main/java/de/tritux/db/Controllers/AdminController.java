package de.tritux.db.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Services.AdminService;
import de.tritux.db.entities.Admin;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.AdminRepository;



@RestController
public class AdminController {
	
	
	private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

	
    @Autowired
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
	
	@GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = adminService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = adminService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
