package de.tritux.db.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Jwt.JwtUtil;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    
	private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder encoder;
    
    @Autowired
    public AuthController(UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }
	
    @GetMapping("/info")
    public String welcome(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Supprimez le préfixe "Bearer " du header Authorization
        String username = jwtUtil.extractUsername(token);

        return "Welcome To Tritux, " + username + "!!";
    }

	
	 @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        User user = userRepository.findByNom(authRequest.getNom());
	        
	        if (user == null || !encoder.matches(user.getPassword(),authRequest.getPassword())) {
	            throw new Exception("Invalid username/password");
	        }

	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getNom(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("Invalid username/password");
	        }
	        
	        return jwtUtil.generateToken(authRequest.getNom());
	    }
	}
	
	
	
	
	
