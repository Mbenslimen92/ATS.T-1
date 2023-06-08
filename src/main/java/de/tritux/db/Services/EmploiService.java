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

	}
