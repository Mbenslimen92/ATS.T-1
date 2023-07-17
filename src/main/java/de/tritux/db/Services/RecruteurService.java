package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;


import de.tritux.db.entities.Recruteur;
import de.tritux.db.repositories.RecruteurRepository;



@Service
public class RecruteurService extends UserService{
	private final RecruteurRepository recruteurRepository;
    
    
    

	
    public RecruteurService(RecruteurRepository recruteurRepository) {
		super();
		this.recruteurRepository = recruteurRepository;
		
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
	
	
  
   
	
}


