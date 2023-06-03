package de.tritux.db.Services;

import java.util.List;


import org.springframework.stereotype.Service;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.authentication.UserAuthentication;
import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;
import de.tritux.db.entities.User;
import de.tritux.db.repositories.EmploiRepository;
import de.tritux.db.repositories.RecruteurRepository;
import de.tritux.db.repositories.UserRepository;



@Service
public class RecruteurService extends UserService{
	private final RecruteurRepository recruteurRepository;
    private final EmploiRepository emploiRepository;

    public RecruteurService(UserRepository userRepository, RecruteurRepository recruteurRepository, EmploiRepository emploiRepository) {
        super(userRepository);
        this.recruteurRepository = recruteurRepository;
        this.emploiRepository = emploiRepository;
    }

	
    public List<Recruteur> getAllRecruteur() {
        return recruteurRepository.findAll();
    }

    public Recruteur updateRecruteur(Recruteur recruteur) {
        return recruteurRepository.save(recruteur);
    }


	public Recruteur getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Recruteur saveRecruteur(Recruteur recruteur) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	public EmploiRepository getEmploiRepository() {
		return emploiRepository;
	}
	
	public Emploi obtenirOffreEmploiParId(Integer offreId) {
        return emploiRepository.findById(offreId)
                .orElseThrow(() -> new de.tritux.db.Exception.NotFoundException("Offre d'emploi introuvable"));
    }
	
    public Emploi ajouterOffreEmploi(Emploi emploi) {
        return emploiRepository.save(emploi);
    }

	
	public Emploi modifierOffreEmploiParId(Integer offreId, Emploi emploiModifier) {
        Emploi emploi = emploiRepository.findById(offreId)
                .orElseThrow(() -> new IllegalArgumentException("Offre d'emploi introuvable"));

        emploi.setTitre(emploiModifier.getTitre());
        emploi.setDescription(emploiModifier.getDescription());
        emploi.setDate_de_publication(emploiModifier.getDate_de_publication());

        return emploiRepository.save(emploi);
    }

    public void supprimerOffreEmploiParId(Integer offreId) {
        Emploi emploi = emploiRepository.findById(offreId)
                .orElseThrow(() -> new IllegalArgumentException("Offre d'emploi introuvable"));

        emploiRepository.delete(emploi);
    }

    public List<Emploi> obtenirTousLesOffresEmploi() {
        return emploiRepository.findAll();
    }

	
}
/*public void register(String nom, String prenom, String mail, Long tel, String password) throws UserAlreadyExistsException {
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
    public void login(String mail, String password) {
        if (UserAuthentication.authenticate(mail, password)) {
            System.out.println("Connexion réussie en tant que Recruteur");
        } else {
            System.out.println("Échec de la connexion en tant que Recruteur. Veuillez vérifier vos informations d'identification.");
        }
    }*/

