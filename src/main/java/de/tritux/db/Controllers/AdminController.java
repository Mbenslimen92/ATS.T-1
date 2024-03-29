package de.tritux.db.Controllers;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import de.tritux.db.entities.Admin;



@RestController
@RequestMapping("/admins")

@CrossOrigin("*")
public class AdminController {
    
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmin();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Admin createNewAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getOne(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public Admin updateAdminById(@PathVariable Integer id, @RequestBody Admin adminDetails) {
        Admin admin = adminService.getOne(id);
        // Update fields of admin here
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteAdminById(@PathVariable Integer id) {
        adminService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}



	

	

