package de.tritux.db.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Services.UserInscription;
import de.tritux.db.Services.UserService;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;

@RestController
@RequestMapping
public class UserController {
	
	
	
	@Autowired
	private UserRepository userRepository;


	
	
	@GetMapping(value="/users")
	public List<User> users(){
		return userRepository.findAll();
		
	}
	@GetMapping(value="/users/{id}")
	public User listUser(@PathVariable(name="id") Integer id){
		return userRepository.findById(id).get();
		
	}
	@PutMapping(value="/users/{id}")
	public User update(@PathVariable(name="id") Integer id,@RequestBody User u){
		u.setId(id);
		return userRepository.save(u);
		
	}
	@PostMapping(value="/users")
	public User save(@RequestBody User u){

		return userRepository.save(u);
	}
	@DeleteMapping(value="/users/{id}")
	public void delete(@PathVariable(name="id") Integer id){
		
		userRepository.deleteById(id);
	}
	
	
	@Autowired
    private UserInscription userInscription;

    @PostMapping("/inscription")
    public ResponseEntity<String> registerUser(@RequestParam("nom") String nom,
                                               @RequestParam("prenom") String prenom,
                                               @RequestParam("mail") String mail,
                                               @RequestParam("tel") Long tel,
                                               @RequestParam("password") String password) {
        User newUser = new User();
        newUser.setnom(nom);
        newUser.setPrenom(prenom);
        newUser.setMail(mail);
        newUser.setTel(tel);
        newUser.setPassword(password);
        
        boolean registrationSuccess = userInscription.register(newUser);
        
        if (registrationSuccess) {
            return ResponseEntity.ok("Inscription réussie !");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'inscription.");
        }
    }
    
    
}
	

