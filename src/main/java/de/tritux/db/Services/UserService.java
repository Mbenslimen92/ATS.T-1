package de.tritux.db.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.authentication.UserAuthentication;
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

	public  User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void InscriptionUser(String nom, String prenom, String mail, Long tel, String password) throws UserAlreadyExistsException {
        UserInscription userInscription = new UserInscription(null);
        User newUser = userInscription.saveUser(nom, prenom, mail, tel, password);

        try {
            boolean registrationSuccess = userInscription.register(newUser);

            if (registrationSuccess) {
                System.out.println("Inscription réussie !");
            } else {
                System.out.println("L'inscription a échoué. Veuillez réessayer.");
            }
        } catch (UserAlreadyExistsException e) {
            System.out.println("L'inscription a échoué. L'utilisateur existe déjà.");
        }
    }
    public void Userlogin(String mail, String password) {
        if (UserAuthentication.authenticate(mail, password)) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Échec de la connexion. Veuillez vérifier vos informations d'identification.");
        }
    }

}







