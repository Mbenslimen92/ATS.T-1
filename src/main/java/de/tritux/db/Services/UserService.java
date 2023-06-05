package de.tritux.db.Services;



import org.springframework.stereotype.Service;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.authentication.UserAuthentication;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;

@Service
public class UserService {
    public final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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







