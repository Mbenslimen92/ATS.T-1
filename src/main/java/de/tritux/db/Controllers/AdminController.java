package de.tritux.db.Controllers;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Services.AdminService;
import de.tritux.db.entities.Admin;



@RequestMapping("/admins")
@RestController
public class AdminController {
    
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Get all Admins
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmin();
    }

    // Create a new Admin
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Admin createNewAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    // Get a single Admin
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getOne(id);
    }

    // Update a Admin
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public Admin updateAdminById(@PathVariable Integer id, @RequestBody Admin adminDetails) {
        Admin admin = adminService.getOne(id);
        // Update fields of admin here
        return adminService.updateAdmin(admin);
    }

    // Delete a Admin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteAdminById(@PathVariable Integer id) {
        adminService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}



	/*@GetMapping(value="/admins")
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
	}*/

	

