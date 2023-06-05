package de.tritux.db.Services;

import org.springframework.stereotype.Service;

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

@Service
public class CandidatureService {
    
    CandidatRepository candidatRepository;
    EmploiRepository emploiRepository;
    CandidatureRepository candidatureRepository;
    private Emploi emploi;

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
    
    

    public void processLinkedInCandidature(String src) throws IOException {
        Candidature candidature = new Candidature();

        if (src != null && src.startsWith("https://www.linkedin.com")) {
            // Scraper les informations du profil LinkedIn
            Integer candidatId = candidature.getCandidat().getId();
            Candidat candidat = candidatRepository.findById(candidatId)
                    .orElseThrow(() -> new NotFoundException("Candidat introuvable"));

            String profilLinkedInUrl = src;

            // Effectuer le scraping avec Jsoup pour extraire les informations du profil LinkedIn
            Document document = Jsoup.connect(profilLinkedInUrl).get();

            String nom = document.select("span.full-name").text();
            String resume = document.select("p.resume").text(); // Assuming resume is in the Candidat class

            // Créer une nouvelle candidature avec les informations extraites
            Candidature candidature1 = new Candidature();
            candidature.setCandidat(candidat);
            candidature.setEmploi(emploi);
            candidature.getCandidat().setnom(nom); // Set the nom attribute of the Candidat class
            candidature.getCandidat().setResume(resume); // Set the resume attribute of the Candidat class

            // Sauvegarder la candidature dans la base de données
            candidatureRepository.save(candidature);

            // Extract relevant candidates based on job description
            // Utilize other attributes such as resume or universite for relevance criteria
            List<Candidat> relevantCandidates = candidatRepository.findByResumeContaining(resume);

            // Create and save Candidature for each relevant candidate
            for (Candidat relevantCandidate : relevantCandidates) {
                Candidature relevantCandidature = new Candidature();
                relevantCandidature.setCandidat(relevantCandidate);
                relevantCandidature.setEmploi(emploi);
                relevantCandidature.getCandidat().setnom(relevantCandidate.getnom());
                relevantCandidature.getCandidat().setResume(relevantCandidate.getResume());
                candidatureRepository.save(relevantCandidature);
            }
        } else {
            // Gérer d'autres sources de candidature ou renvoyer une exception si aucune source n'est spécifiée
            throw new UnsupportedOperationException("Source de candidature non prise en charge");
        }
    }

    }

