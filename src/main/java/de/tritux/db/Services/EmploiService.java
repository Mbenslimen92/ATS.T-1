package de.tritux.db.Services;

import java.util.List;


import org.springframework.stereotype.Service;


import de.tritux.db.entities.Emploi;

import de.tritux.db.repositories.EmploiRepository;

@Service
public class EmploiService {
	
		private final EmploiRepository emploiRepository;

	    public EmploiService(EmploiRepository emploiRepository) {
	        this.emploiRepository = emploiRepository;
	    }
		
	    public List<Emploi> getAllEmploin() {
	        return emploiRepository.findAll();
	    }

	    public Emploi updateEmploi(Emploi emploi) {
	        return emploiRepository.save(emploi);
	    }


		public Emploi getOne(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}

		public Emploi saveEmploi(Emploi emploi) {
			// TODO Auto-generated method stub
			return null;
		}

		public void deleteById(Integer id) {
			// TODO Auto-generated method stub
			
		}
	}
