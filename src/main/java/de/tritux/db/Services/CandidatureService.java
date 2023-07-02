package de.tritux.db.Services;


import org.springframework.stereotype.Service;


import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.MotCle.MotCle;
import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Candidature;
import de.tritux.db.entities.Emploi;
import de.tritux.db.repositories.CandidatRepository;
import de.tritux.db.repositories.CandidatureRepository;
import de.tritux.db.repositories.EmploiRepository;
import de.tritux.db.repositories.MotCleRepository;

import java.io.IOException;

import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;



@Service
public class CandidatureService {

    private CandidatRepository candidatRepository;
    private EmploiRepository emploiRepository;
    private CandidatureRepository candidatureRepository;
    private MotCleRepository motCleRepository;

    public CandidatureService(CandidatRepository candidatRepository, EmploiRepository emploiRepository,
                              CandidatureRepository candidatureRepository, MotCleRepository motCleRepository) {
        this.candidatRepository = candidatRepository;
        this.emploiRepository = emploiRepository;
        this.candidatureRepository = candidatureRepository;
        this.motCleRepository = motCleRepository;
    }
   
    
    
    
    public Candidature postulerOffreEmploi(Integer candidatId, Integer emploiId) {
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new NotFoundException("Candidat introuvable"));

        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

        Candidature candidature = new Candidature();
        candidature.setCandidat(candidat);
        candidature.setEmploi(emploi);
        candidatureRepository.save(candidature);

        return candidature;
    }

  
    
    public Candidat extractCandidateInformation(String profileUrl) throws IOException {
        Document document = Jsoup.connect(profileUrl).get();

        String nom = document.select("span.full-name").text();
        String resume = document.select("p.resume").text();
        String experience = document.select("ul.experience li").text();
        String competences = document.select("ul.skills li").text();
        String formations = document.select("ul.education li").text();

        Candidat candidat = new Candidat();
        candidat.setNom(nom);
        candidat.setResume(resume);
        candidat.setExperiences(experience);
        candidat.setCompetences(competences);
        candidat.setFormations(formations);

        return candidat;
    }

    public void processLinkedInCandidature(String src, Integer emploiId) throws IOException {
        if (src != null && src.startsWith("https://www.linkedin.com")) {
            Candidat candidat = extractCandidateInformation(src);
            Emploi emploi = emploiRepository.findById(emploiId)
                    .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

            Candidature nouvelleCandidature = new Candidature();
            nouvelleCandidature.setCandidat(candidat);
            nouvelleCandidature.setEmploi(emploi);

            if (!candidatureRepository.existsByCandidatAndEmploi(candidat, emploi)) {
                candidatureRepository.save(nouvelleCandidature);
            }

            List<Candidat> candidatRecommandés = candidatRepository.findByResumeContaining(candidat.getResume());

            for (Candidat candidatRecommandé : candidatRecommandés) {
                if (!candidatureRepository.existsByCandidatAndEmploi(candidatRecommandé, emploi)) {
                    Candidature candidatureRecommandé = new Candidature();
                    candidatureRecommandé.setCandidat(candidatRecommandé);
                    candidatureRecommandé.setEmploi(emploi);
                    candidatureRepository.save(candidatureRecommandé);
                }
            }
        } else {
            throw new UnsupportedOperationException("Source de candidature non prise en charge");
        }
    }
    
    
    
    public void rechercherEtSauvegarderCandidats(List<Candidat> candidates, Integer emploiId, List<String> motsCles) {
        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

        List<Candidat> candidatRecommandes = new ArrayList<>();

        for (Candidat candidat : candidates) {
            String resumeCandidat = candidat.getResume();

            // Vérifier si le résumé du candidat contient au moins un des mots-clés
            for (String motCleValeur : motsCles) {
                MotCle motCle = motCleRepository.findByValeurMC(motCleValeur);
                if (motCle != null && resumeCandidat.toLowerCase().contains(motCleValeur.toLowerCase())) {
                    candidatRecommandes.add(candidat);
                    break; // Sortir de la boucle dès qu'un mot-clé est trouvé
                }
            }
        }

        for (Candidat candidatRecommande : candidatRecommandes) {
            Candidature candidature = new Candidature();
            candidature.setCandidat(candidatRecommande);
            candidature.setEmploi(emploi);

            if (!candidatureRepository.existsByCandidatAndEmploi(candidatRecommande, emploi)) {
                candidatureRepository.save(candidature);
            }
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




