package de.tritux.db.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Jwt.JwtUtil;

@RestController
public class AuthController {
	
	
	@Autowired
    private JwtUtil jwtUtil;
	
	
	@Autowired
    private AuthenticationManager authenticationManager;

	
	 @GetMapping("/")
	    public String welcome() {
	        return "Welcome !!";
	    }
	
	 @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getNom(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        return jwtUtil.generateToken(authRequest.getNom());
	    }
	}
	
	
	
	
	
