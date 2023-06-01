package de.tritux.db.Services;

import org.springframework.stereotype.Service;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Candidature;
import de.tritux.db.entities.Emploi;
import de.tritux.db.repositories.CandidatRepository;
import de.tritux.db.repositories.CandidatureRepository;
import de.tritux.db.repositories.EmploiRepository;

@Service
public class CandidatureService {
    
CandidatRepository candidatRepository;
EmploiRepository emploiRepository;
CandidatureRepository candidatureRepository;

    public Candidature postulerOffreEmploi(Integer candidatId, Integer emploiId) {
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new NotFoundException("Candidat introuvable"));

        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

        Candidature candidature = new Candidature();
        candidature.setCandidat(candidat);
        candidature.setEmploi(emploi);

        return candidatureRepository.save(candidature);
    }
}