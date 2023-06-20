package de.tritux.db.Controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.Inscription.UserInscription;
import de.tritux.db.Services.UserService;
import de.tritux.db.entities.User;


@RestController
@RequestMapping
public class UserController {
	
	UserService userService;
	


	
	
	
	
	@Autowired
    private UserInscription userInscription;

	
	
	@PostMapping("/inscription")
    public ResponseEntity<String> InscriptionUser(@RequestBody User user) {
        try {
            boolean registrationSuccess = userInscription.register(user);

            if (registrationSuccess) {
                return ResponseEntity.ok("Inscription réussie !");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'inscription a échoué. Veuillez réessayer.");
            }
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
   
    
    
}
	

