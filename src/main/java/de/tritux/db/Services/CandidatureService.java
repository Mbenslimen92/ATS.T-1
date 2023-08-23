package de.tritux.db.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Candidature;
import de.tritux.db.entities.Emploi;
import de.tritux.db.repositories.CandidatRepository;
import de.tritux.db.repositories.CandidatureRepository;
import de.tritux.db.repositories.EmploiRepository;

import java.io.IOException;

import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;



@Service
public class CandidatureService {

    final private CandidatRepository candidatRepository;
   final  private EmploiRepository emploiRepository;
   final private CandidatureRepository candidatureRepository;

    public CandidatureService(CandidatRepository candidatRepository, EmploiRepository emploiRepository,
                              CandidatureRepository candidatureRepository) {
        this.candidatRepository = candidatRepository;
        this.emploiRepository = emploiRepository;
        this.candidatureRepository = candidatureRepository;
    }
   
    
    
    
    

    public ResponseEntity<String> postulerOffreEmploi(Integer candidatId, Integer emploiId) {
        try {
            Candidat candidat = candidatRepository.findById(candidatId)
                    .orElseThrow(() -> new NotFoundException("Candidat introuvable"));

            Emploi emploi = emploiRepository.findById(emploiId)
                    .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

            boolean candidatureExist = candidatureRepository.existsByCandidatAndEmploi(candidat, emploi);
            if (candidatureExist) {
                throw new IllegalStateException("Le candidat a déjà postulé à cette offre d'emploi");
            }

            Candidature candidature = new Candidature();
            candidature.setCandidat(candidat);
            candidature.setEmploi(emploi);
            candidatureRepository.save(candidature);

            return ResponseEntity.ok("Candidature effectuée avec succès.");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    


  
    
    
    
    







    public List<Candidature> getCandidaturesByEmploiId(Integer emploiId) {
        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

        return candidatureRepository.findByEmploi(emploi);
    }

    public void deleteCandidature(Integer candidatureId) {
        candidatureRepository.deleteById(candidatureId);
    }

    public Candidature getCandidatureById(Integer candidatureId) {
        return candidatureRepository.findById(candidatureId)
                .orElseThrow(() -> new NotFoundException("Candidature introuvable"));
    }

    public List<Candidature> getCandidaturesByCandidatId(Integer candidatId) {
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new NotFoundException("Candidat introuvable"));

        return candidatureRepository.findByCandidat(candidat);
    }

    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }
}




