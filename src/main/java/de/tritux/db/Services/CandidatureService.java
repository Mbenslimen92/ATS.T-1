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
import java.net.URLEncoder;


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

  
    
  
    public void scraperLinkedInPourProfiles(Emploi emploi) {
        String motsClesString = emploi.getMotsCles();
        if (motsClesString == null || motsClesString.isEmpty()) {
            System.out.println("Aucun mot-clé spécifié dans l'offre d'emploi.");
            return;
        }

        List<String> motsCles = Arrays.asList(motsClesString.split("\\s*,\\s*"));

        StringBuilder searchKeywords = new StringBuilder();
        for (String motCle : motsCles) {
            searchKeywords.append(motCle).append(" ");
        }


            String searchUrl = "https://www.linkedin.com/search/results/people/?keywords=" + searchKeywords.toString().trim().replace(" ", "%20");

            int pageCount = 0;
            int resultCount = 0;

            try {
                while (resultCount < 100) {
                    String pageUrl = searchUrl + "&page=" + (pageCount + 1);
                    Document document = Jsoup.connect(pageUrl).get();
                    Elements profileElements = document.select(".search-result__wrapper");

                    for (Element element : profileElements) {
                        String name = element.select(".actor-name").text();
                        String headline = element.select(".subline-level-1").text();
                        String location = element.select(".subline-level-2").text();

                        System.out.println("Name: " + name);
                        System.out.println("Headline: " + headline);
                        System.out.println("Location: " + location);
                        System.out.println("------------------------");

                        resultCount++;
                        if (resultCount >= 100) {
                            break;
                        }
                    }

                    pageCount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ...
    







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




