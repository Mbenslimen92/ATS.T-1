package de.tritux.db.Services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import de.tritux.db.entities.User;
@Service
public class UserInscription {

    public static void main(String[] args) {
    	
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
        String password = scanner.nextLine();
        
        User newUser = new User();
        newUser.setnom(nom);
        newUser.setPrenom(prenom);
        newUser.setMail(mail);
        newUser.setTel(tel);
        newUser.setPassword(password);
       
        
        System.out.println("Inscription réussie ! Bienvenue, " + prenom + " " + nom + " !");
        
        scanner.close();
    }

    public boolean register(User newUser) {
        try {
           
            if (!validateRegistration(newUser)) {
                return false;
            }
            
           
            if (userExists(newUser.getMail())) {
                System.out.println("Un utilisateur avec cette adresse e-mail existe déjà.");
                return false;
            }
           
            return true;
        } catch (Exception e) {
           
            System.out.println("Une erreur s'est produite lors de l'inscription : " + e.getMessage());
            return false;
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


    public boolean userExists(String mail) {
        User[] userList = getUsers();
        
        for (User user : userList) {
            if (user.getMail().equals(mail)) {
                return true;
            }
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
        
        if (newUser.getPrenom().isEmpty()) {
            System.out.println("Le prénom est requis.");
            return false;
        }
        
        
        return true;
    }


}
