package de.tritux.db.Services;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.entities.Emploi;

import de.tritux.db.models.EmploiDto;
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
		
		public Emploi obtenirOffreEmploiParId(Integer emploiId) {
	        return emploiRepository.findById(emploiId).get();
	               // .orElseThrow(() -> new IllegalArgumentException("Offre d'emploi introuvable"));
	    }
		
	    public Emploi ajouterOffreEmploi(Emploi emploi) {
	        return emploiRepository.save(emploi);
	    }

		
		public Emploi modifierOffreEmploiParId(Integer emploiId, Emploi emploiModifier) {
	        Emploi emploi = emploiRepository.findById(emploiId)
	                .orElseThrow(() -> new IllegalArgumentException("Offre d'emploi introuvable"));

    
	        emploi.setTitre(emploiModifier.getTitre());
	        emploi.setDescription(emploiModifier.getDescription());
	        emploi.setDateDePublication(emploiModifier.getDateDePublication());
	        emploi.setMotsCles(emploiModifier.getMotsCles());
	        emploi.setMission(emploiModifier.getMission());
	        emploi.setExigencesDeLemploi(emploiModifier.getExigencesDeLemploi());
	        emploi.setEducation(emploiModifier.getEducation());
	        emploi.setCompetence(emploiModifier.getCompetence());
	        emploi.setExperience(emploiModifier.getExperience());
	        emploi.setLocalisation(emploiModifier.getLocalisation());
	        emploi.setPostes_vacants(emploiModifier.getPostes_vacants());
	        emploi.setType_emploi(emploiModifier.getType_emploi());
	        emploi.setGenre(emploiModifier.getGenre());
	        emploi.setDate_expiration(emploiModifier.getDate_expiration());
	        
	        return emploiRepository.save(emploi);
	    }

	    public void supprimerOffreEmploiParId(Integer emploiId) {
	        Emploi emploi = emploiRepository.findById(emploiId)
	                .orElseThrow(() -> new IllegalArgumentException("Offre d'emploi introuvable"));

	        emploiRepository.delete(emploi);
	    }
	    

	    public List<EmploiDto> getAllEmploi() {
	    	List<Emploi> list = emploiRepository.findAllByOrderByIdDesc();
	    	List<EmploiDto> result = list.stream().map(emploi -> {
	    		
	        	EmploiDto emploiDto = new EmploiDto();
	        	emploiDto.setId(emploi.getId());
	        	emploiDto.setTitre(emploi.getTitre());
	        	emploiDto.setDateDePublication(emploi.getDateDePublication());
	        	emploiDto.setDescription(emploi.getDescription());
	        	emploiDto.setMotsCles(emploi.getMotsCles());

	        	return emploiDto;
	        	
}).collect(Collectors.toList());
	        
	        return result;
	    }

	        	
	    		
	    
	    public List<Emploi> obtenirOffreEmploiParDatePublication(String datePublication) {
	        return emploiRepository.findByDateDePublication(datePublication);
	               // .orElseThrow(() -> new IllegalArgumentException("Offre d'emploi introuvable"));
	    }

		/*public List<EmploiDto> obtenirOffreEmploiParDatePublication() {
			// TODO Auto-generated method stub
			return null;
		}*/
		
	    
	    
	}
