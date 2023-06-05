package de.tritux.db.Controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.Services.UserInscription;
import de.tritux.db.Services.UserService;
import de.tritux.db.authentication.UserAuthentication;
import de.tritux.db.entities.User;


@RestController
@RequestMapping
public class UserController {
	
	UserService userService;
	


	
	
	
	
	@Autowired
    private UserInscription userInscription;

	@PostMapping("/inscription")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
	    try {
	        userInscription.register(user);
	        return ResponseEntity.ok("Inscription réussie !");
	    } catch (UserAlreadyExistsException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

   
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("mail") String mail, @RequestParam("password") String password) {
        boolean authenticated = UserAuthentication.authenticate(mail, password);
        if (authenticated) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    
}
	

