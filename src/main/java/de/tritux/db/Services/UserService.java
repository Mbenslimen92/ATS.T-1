package de.tritux.db.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }


	public User getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void registerUser(User user) {
        // Vérifiez si l'utilisateur existe déjà en utilisant l'adresse e-mail
        Optional<User> existingUser = userRepository.findByMail(user.getMail());
        if (existingUser.isPresent()) {
            // Gérez le cas où l'utilisateur existe déjà
            // Lancez une exception, renvoyez un message d'erreur, etc.
        } else {
            // Enregistrez le nouvel utilisateur
            userRepository.save(user);
        }
    }
	
	 public User[] getUsers() {
	        
	        User[] userList = {
	            new User(1, "med", "benslimen", "benslimen92@gmail.com.com", (long) 22781222, "xxx"),
	            new User(2, "yyy", "yyyy", "yyy@y.com", (long) 11111111, "yyy"),
	            new User(3, "zzz", "zzzz", "zzz@y.com", (long) 22222222, "zzz")
	        };
	        
	        return userList;
	    }
	
}







