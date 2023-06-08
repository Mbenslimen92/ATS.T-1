package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Recruteur;

import de.tritux.db.repositories.CandidatRepository;
import de.tritux.db.repositories.UserRepository;

@Service
public class CandidatService extends UserService {
    private CandidatRepository candidatRepository;

    public CandidatService(UserRepository userRepository, CandidatRepository candidatRepository) {
        super(userRepository);
        this.candidatRepository = candidatRepository;
    }

    public List<Candidat> getAllCandidat() {
        return candidatRepository.findAll();
    }

    public Candidat updateCandidat(Recruteur recruteur) {
        return candidatRepository.save(recruteur);
    }

    public Candidat getOne(Integer id) {
        Integer candidatId = id; 
        return candidatRepository.findById(candidatId).orElse(null);
    }

    public Candidat saveCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public void deleteById(Integer id) {
        candidatRepository.deleteById(id);
    }
}
