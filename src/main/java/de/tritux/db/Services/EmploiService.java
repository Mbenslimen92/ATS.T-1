package de.tritux.db.Services;



import java.util.List;

import org.springframework.stereotype.Service;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.entities.Emploi;

import de.tritux.db.repositories.EmploiRepository;

@Service
public class EmploiService {
	
		private final EmploiRepository emploiRepository;

	    public EmploiService(EmploiRepository emploiRepository) {
	        this.emploiRepository = emploiRepository;
	    }
		
	    public String getDescriptionById(Integer emploiId) {
	        Emploi emploi = emploiRepository.findById(emploiId)
	            .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

	        return emploi.getDescription();
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
