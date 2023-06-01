package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.tritux.db.entities.Admin;

import de.tritux.db.repositories.AdminRepository;


@Service
public class AdminService {

	private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
	
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


	public Admin getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Admin saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
