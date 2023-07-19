package de.tritux.db.Controllers;

import java.util.List;

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
import de.tritux.db.models.UserDto;


@RestController
public class AdminController {
	
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping(value="/admins")
	public List<Admin> getAdmins(){
		return adminService.getAllAdmin();
	}

	@GetMapping(value="/admins/{id}")
	public Admin getAdmin(@PathVariable(name="id") Integer id){
		return adminService.getOne(id);
	}

	@PutMapping(value="/admins/{id}")
	public Admin updateAdmin(@PathVariable(name="id") Integer id, @RequestBody Admin a){
		a.setId(id);
		return adminService.updateAdmin(a);
	}

	@PostMapping(value="/admins")
	public Admin saveAdmin(@RequestBody Admin a){
		return adminService.saveAdmin(a);
	}

	@DeleteMapping(value="/admins/{id}")
	public void deleteAdmin(@PathVariable(name="id") Integer id){
		adminService.deleteById(id);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		
		List<UserDto> users = adminService.getAllUsers();
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
