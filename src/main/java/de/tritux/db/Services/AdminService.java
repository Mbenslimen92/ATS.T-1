package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;


import de.tritux.db.entities.Admin;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.AdminRepository;
import de.tritux.db.repositories.UserRepository;


@Service
public class AdminService extends UserService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    public AdminService(UserRepository userRepository, AdminRepository adminRepository) {
       this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }
    
    
	
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getOne(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }
    

   
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
  
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
