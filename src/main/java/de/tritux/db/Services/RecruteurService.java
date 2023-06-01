package de.tritux.db.Services;

import java.util.List;


import org.springframework.stereotype.Service;

import de.tritux.db.entities.Emploi;
import de.tritux.db.entities.Recruteur;
import de.tritux.db.repositories.EmploiRepository;
import de.tritux.db.repositories.RecruteurRepository;



@Service
public class RecruteurService {

	private final RecruteurRepository recruteurRepository;
	private final EmploiRepository emploiRepository;
    public RecruteurService(RecruteurRepository recruteurRepository, EmploiRepository emploiRepository) {
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

	
	public Emploi modifierOffreEmploiParId(Integer offreId, Emploi emploiModifie) {
        Emploi emploi = emploiRepository.findById(offreId)
                .orElseThrow(() -> new IllegalArgumentException("Offre d'emploi introuvable"));

        emploi.setTitre(emploiModifie.getTitre());
        emploi.setDescription(emploiModifie.getDescription());
        emploi.setDate_de_publication(emploiModifie.getDate_de_publication());

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


