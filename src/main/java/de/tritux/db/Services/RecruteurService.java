package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;


import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;
import de.tritux.db.repositories.EmploiRepository;
import de.tritux.db.repositories.RecruteurRepository;
import de.tritux.db.repositories.UserRepository;



@Service
public class RecruteurService extends UserService{
	private final RecruteurRepository recruteurRepository;
    private final EmploiRepository emploiRepository;
    private final UserRepository userRepository;
    
    

	
    public RecruteurService(RecruteurRepository recruteurRepository, EmploiRepository emploiRepository,
			UserRepository userRepository) {
		super();
		this.recruteurRepository = recruteurRepository;
		this.emploiRepository = emploiRepository;
		this.userRepository = userRepository;
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
        emploi.setDateDePublication(emploiModifier.getDateDePublication());

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


