package de.tritux.db.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.tritux.db.Role;
import de.tritux.db.Jwt.JwtRequest;
import de.tritux.db.Jwt.JwtResponse;
import de.tritux.db.Jwt.JwtUtil;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.RoleRepository;
import de.tritux.db.repositories.UserRepository;


   
	
	
    
    import java.util.HashSet;
import java.util.List;
import java.util.Set;

    @Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

       
		public void initRoleAndUser() {

            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setRoleName("User");
            userRole.setRoleDescription("Default role for newly created record");
            roleRepository.save(userRole);

            User adminUser = new User();
            adminUser.setNom("benslimen");
            adminUser.setPrenom("med");
            adminUser.setMail("benslimen92@gmail.com");
            adminUser.setTel((long) 22781222);
            adminUser.setPassword(getEncodedPassword("admin@pass"));
            
            adminUser.setPrenom("bensli");
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            adminUser.setRole(adminRoles);
            userRepository.save(adminUser);


        }

        public User registerNewUser(User user) {
            Role role = roleRepository.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setPassword(getEncodedPassword(user.getPassword()));

            return userRepository.save(user);
        }

        public String getEncodedPassword(String password) {
            return passwordEncoder.encode(password);
        }

        
        }


	
	
	
	








