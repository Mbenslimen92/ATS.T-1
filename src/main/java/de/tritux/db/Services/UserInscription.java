package de.tritux.db.Services;


import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.UserRepository;


@Service
public class UserInscription {

    @Autowired
    public UserInscription(UserService userService) {
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
        newUser.setnom(nom);
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
        
   
        if (newUser.getnom().length() < 2) {
            System.out.println("Le nom doit avoir au moins 2 caractères.");
            return false;
        }
        
        if (newUser.getPrenom().length() < 2) {
            System.out.println("Le prénom doit avoir au moins 2 caractères.");
            return false;
        }
        
        
        return true;
    }

    
UserRepository userRepository;
    public boolean register(User newUser) throws UserAlreadyExistsException {
        try {
            if (!validateRegistration(newUser)) {
                return false;
            }

            Optional<User> existingUser = userRepository.findByMail(newUser.getMail());
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
		// TODO Auto-generated method stub
		return null;
	}

	
	
    
    
}











/*@Service

public class UserInscription {

    public void runInscription() {
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
        scanner.nextLine(); // Consommer la nouvelle ligne
        String password = scanner.nextLine();
        
        User newUser = new User();
        newUser.setnom(nom);
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

    public boolean register(User newUser) {
        try {
            if (!validateRegistration(newUser)) {
                return false;
            }
            
            User[] userList = getUsers();
            for (User user : userList) {
                if (user.getMail().equals(newUser.getMail())) {
                    System.out.println("Un utilisateur avec cette adresse e-mail existe déjà.");
                    return false;
                }
            }
            
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_benz", "root", "root")) {
                String insertQuery = "INSERT INTO User (nom, prenom, mail, tel, password) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                    statement.setString(1, newUser.getnom());
                    statement.setString(2, newUser.getPrenom());
                    statement.setString(3, newUser.getMail());
                    statement.setLong(4, newUser.getTel());
                    statement.setString(5, newUser.getPassword());
                    
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        // L'enregistrement a été inséré avec succès dans la base de données
                        return true;
                    }
                }
            } 
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de l'inscription : " + e.getMessage());
            return false;
        }
        
        return false;
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
        
   
        if (newUser.getnom().length() < 2) {
            System.out.println("Le nom doit avoir au moins 2 caractères.");
            return false;
        }
        
        if (newUser.getPrenom().length() < 2) {
            System.out.println("Le prénom doit avoir au moins 2 caractères.");
            return false;
        }
        
        
        return true;
    }

	public void insertUsers() {
	    User[] users = getUsers();
	    
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_benz", "root", "root")) {
	        String insertQuery = "INSERT INTO User (nom, prenom, mail, tel, password) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
	            for (User user : users) {
	                statement.setString(1, user.getnom());
	                statement.setString(2, user.getPrenom());
	                statement.setString(3, user.getMail());
	                statement.setLong(4, user.getTel());
	                statement.setString(5, user.getPassword());
	                
	                statement.executeUpdate();
	            }
	        }
	        
	        System.out.println("Les utilisateurs ont été insérés dans la base de données.");
	    } catch (SQLException e) {
	        System.out.println("Une erreur s'est produite lors de l'insertion des utilisateurs : " + e.getMessage());
	    }
	}
public User[] getUsers() {
        
        User[] userList = {
        		new User(1, "med", "benslimen", "benslimen92@gmail.com", 22781222L, "xxx"),
                new User(2, "yyy", "yyyy", "yyy@y.com", 11111111L, "yyy"),
                new User(3, "zzz", "zzzz", "zzz@y.com", 22222222L, "zzz")
        };
        
        return userList;
    }
}
*/