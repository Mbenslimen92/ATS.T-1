package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.authentication.UserAuthentication;
import de.tritux.db.entities.Admin;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.AdminRepository;
import de.tritux.db.repositories.UserRepository;


@Service
public class AdminService extends UserService {
    private final AdminRepository adminRepository;

    public AdminService(UserRepository userRepository, AdminRepository adminRepository) {
        super(userRepository);
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
