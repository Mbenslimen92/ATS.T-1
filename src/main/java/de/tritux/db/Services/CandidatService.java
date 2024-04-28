package de.tritux.db.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.tritux.db.entities.Candidat;
import de.tritux.db.repositories.CandidatRepository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Optional;
import java.util.List;

@Service
public class CandidatService {
    private final CandidatRepository candidatRepository;



    @Autowired
    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
      
    }

    public List<Candidat> getAllCandidat() {
        return candidatRepository.findAll();
    }

    public Candidat updateCandidat(Candidat c) {
        return candidatRepository.save(c);
    }

    public Candidat getOne(Integer id) {
        return candidatRepository.findById(id).orElse(null);
    }

    public Candidat saveCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public void deleteById(Integer id) {
        candidatRepository.deleteById(id);
    }

    

    

}
