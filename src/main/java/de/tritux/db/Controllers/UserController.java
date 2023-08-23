package de.tritux.db.Controllers;





import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import de.tritux.db.Services.AdminService;
import de.tritux.db.entities.User;
import de.tritux.db.models.UserDto;

@CrossOrigin("*")

@RestController
@RequestMapping
public class UserController {
	
	private final AdminService adminService;

	public UserController(AdminService adminService) {
		this.adminService = adminService;
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
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
	    User currentUser = adminService.getUserById(id);
	    if (currentUser != null) {
	        updatedUser.setId(id); // Ensure the ID is preserved
	        User savedUser = adminService.saveUser(updatedUser); // Assumes saveUser updates when ID already exists
	        return ResponseEntity.ok(savedUser);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		adminService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
   
    
    
}
	

