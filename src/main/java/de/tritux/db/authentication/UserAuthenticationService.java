package de.tritux.db.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.tritux.db.JwtUtil.JwtUtil;
import de.tritux.db.entities.Admin;
import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Recruteur;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;

@Service
public class UserAuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    public UserAuthenticationService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(String mail, String password) {
        User user = userRepository.findByMail(mail);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String role = determineUserRole(user);
            return jwtUtil.generateToken(mail, role);
        }
        return null;
    }

    private String determineUserRole(User user) {
        if (user instanceof Candidat) {
            return "ROLE_CANDIDAT";
        } else if (user instanceof Recruteur) {
            return "ROLE_RECRUTEUR";
        } else if (user instanceof Admin) {
            return "ROLE_ADMIN";
        }
        return "ROLE_USER";
    }
}
