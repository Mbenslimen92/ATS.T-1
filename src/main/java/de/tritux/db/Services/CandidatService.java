package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Recruteur;
import de.tritux.db.repositories.CandidatRepository;


@Service
public class CandidatService {

	private final CandidatRepository candidatRepository;

    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }
	
    public List<Candidat> getAllCandidat() {
        return candidatRepository.findAll();
    }

    public Candidat updateCandidat(Recruteur recruteur) {
        return candidatRepository.save(recruteur);
    }


	public Candidat getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Candidat saveCandidat(Recruteur recruteur) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
