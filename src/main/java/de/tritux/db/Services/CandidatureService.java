package de.tritux.db.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.entities.Candidat;
import de.tritux.db.entities.Candidature;
import de.tritux.db.entities.Emploi;
import de.tritux.db.repositories.CandidatRepository;
import de.tritux.db.repositories.CandidatureRepository;
import de.tritux.db.repositories.EmploiRepository;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;




@Service
public class CandidatureService {
    
    private CandidatRepository candidatRepository;
    private EmploiRepository emploiRepository;
    private CandidatureRepository candidatureRepository;

    public CandidatureService(CandidatRepository candidatRepository, EmploiRepository emploiRepository, CandidatureRepository candidatureRepository) {
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

        return candidatureRepository.save(candidature);
    }

    
    
    public void processLinkedInCandidature(String src, Integer emploiId) throws IOException {
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

            // Récupérer l'objet Emploi correspondant à l'identifiant emploiId
            Emploi emploi = emploiRepository.findById(emploiId)
                    .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

            // Créer une nouvelle candidature avec les informations extraites
            Candidature nouvelleCandidature = new Candidature();
            nouvelleCandidature.setCandidat(candidat);
            nouvelleCandidature.setEmploi(emploi);
            nouvelleCandidature.getCandidat().setnom(nom); // Set the nom attribute of the Candidat class
            nouvelleCandidature.getCandidat().setResume(resume); // Set the resume attribute of the Candidat class

            // Sauvegarder la candidature dans la base de données
            candidatureRepository.save(nouvelleCandidature);

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

    public List<Candidat> searchCandidatesOnLinkedIn(String description) {
        String url = "https://api.linkedin.com/v2/people-search?keywords=" + URLEncoder.encode(description, StandardCharsets.UTF_8);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer votre_token_d_authentification");
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(null, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        List<Candidat> candidates = new ArrayList<>();

        // Logic to extract and process the candidates from the response
        JSONObject responseJson = new JSONObject(response.getBody());
        JSONArray profilesArray = responseJson.getJSONArray("profiles");

        for (int i = 0; i < profilesArray.length(); i++) {
            JSONObject profileJson = profilesArray.getJSONObject(i);

            // Extract relevant information from the profile JSON
            String fullName = profileJson.getString("fullName");
            String headline = profileJson.getString("headline");
            String profileUrl = profileJson.getString("profileUrl");

            // Create a new Candidat object and populate it with the extracted information
            Candidat candidat = new Candidat();
            candidat.setnom(fullName);
            candidat.setResume(headline);
            candidat.setProfilLinkedIn(profileUrl);

            // Add the candidat to the list of candidates
            candidates.add(candidat);
        }

        // Return the list of extracted candidates
        return candidates;
    }

    
    


    public Candidat extractCandidateInformation(String profileUrl) throws IOException {
        Document document = Jsoup.connect(profileUrl).get();

        String nom = document.select("span.full-name").text();
        String resume = document.select("p.resume").text();

        Candidat candidat = new Candidat();
        candidat.setnom(nom);
        candidat.setResume(resume);

        // Autres extractions d'informations du profil LinkedIn si nécessaire

        return candidat;
    }

    public void saveCandidates(List<Candidat> candidates, Integer emploiId) {
        Emploi emploi = emploiRepository.findById(emploiId)
            .orElseThrow(() -> new NotFoundException("Offre d'emploi introuvable"));

        for (Candidat candidat : candidates) {
            Candidature candidature = new Candidature();
            candidature.setCandidat(candidat);
            candidature.setEmploi(emploi);
            candidatureRepository.save(candidature);
        }
    }



	public List<Candidature> getCandidaturesByEmploiId(Integer emploiId) {
		// TODO Auto-generated method stub
		return null;
	}



	public void deleteCandidature(Integer candidatureId) {
		// TODO Auto-generated method stub
		
	}



	public Candidature getCandidatureById(Integer candidatureId) {
		// TODO Auto-generated method stub
		return null;
	}



	public List<Candidature> getCandidaturesByCandidatId(Integer candidatId) {
		// TODO Auto-generated method stub
		return null;
	}



	public List<Candidature> getAllCandidatures() {
		// TODO Auto-generated method stub
		return null;
	}
}
















