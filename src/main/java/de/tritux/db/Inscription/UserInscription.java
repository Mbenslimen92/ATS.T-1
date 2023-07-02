package de.tritux.db.Inscription;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.Services.UserService;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;


@Service
public class UserInscription {

	
	    private final UserRepository userRepository;

	    @Autowired
	    public UserInscription(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

    public void runInscription() throws UserAlreadyExistsException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue ! Veuillez fournir les informations suivantes :");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Adresse e-mail : ");
        String mail = scanner.nextLine();

        System.out.print("Numéro de téléphone : ");
        Long tel = scanner.nextLong();

        System.out.print("Mot de passe : ");
        scanner.nextLine();
        String password = scanner.nextLine();

        User newUser = new User();
        newUser.setNom(nom);
        newUser.setPrenom(prenom);
        newUser.setMail(mail);
        newUser.setTel(tel);
        newUser.setPassword(password);

        boolean registrationSuccess = register(newUser);

        if (registrationSuccess) {
            System.out.println("Inscription réussie ! Bienvenue, " + prenom + " " + nom + " !");
        } else {
            System.out.println("L'inscription a échoué. Veuillez réessayer.");
        }

        scanner.close();
    }

    public boolean validateRegistration(User newUser) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!newUser.getMail().matches(emailRegex)) {
            System.out.println("Adresse e-mail invalide");
            return false;
        }

        String password = newUser.getPassword();
        if (password.length() < 8 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            System.out.println("Le mot de passe doit avoir au moins 8 caractères et contenir des lettres et des chiffres.");
            return false;
        }

        if (newUser.getNom().length() < 2) {
            System.out.println("Le nom doit avoir au moins 2 caractères.");
            return false;
        }

        if (newUser.getPrenom().length() < 2) {
            System.out.println("Le prénom doit avoir au moins 2 caractères.");
            return false;
        }

        return true;
    }

    public boolean register(User newUser) throws UserAlreadyExistsException {
        try {
            if (!validateRegistration(newUser)) {
                return false;
            }

            Optional<User> existingUser = Optional.ofNullable(userRepository.findByMail(newUser.getMail()));
            if (existingUser.isPresent()) {
                throw new UserAlreadyExistsException("L'utilisateur existe déjà");
            }

            userRepository.save(newUser);

            return true;
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de l'inscription : " + e.getMessage());
            return false;
        }
    }

    public User saveUser(String nom, String prenom, String mail, Long tel, String password) {
        User newUser = new User();
        newUser.setNom(nom);
        newUser.setPrenom(prenom);
        newUser.setMail(mail);
        newUser.setTel(tel);
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }
}
