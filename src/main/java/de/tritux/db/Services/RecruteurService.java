package de.tritux.db.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.tritux.db.Exception.ResourceNotFoundException;
import de.tritux.db.entities.Recruteur;
import de.tritux.db.repositories.EmploiRepository;
import de.tritux.db.repositories.RecruteurRepository;



@Service
public class RecruteurService {

    private final RecruteurRepository recruteurRepository;

    public RecruteurService(RecruteurRepository recruteurRepository) {
        this.recruteurRepository = recruteurRepository;
    }

    // Fetch all Recruteurs
    public List<Recruteur> getAllRecruteur() {
        return recruteurRepository.findAll();
    }

    // Save a new Recruteur
    public Recruteur saveRecruteur(Recruteur recruteur) {
        return recruteurRepository.save(recruteur);
    }

    // Fetch a single Recruteur by id
    public Recruteur getOne(Integer id) {
        return recruteurRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Recruteur", "id", id));
    }

    // Update a Recruteur
    public Recruteur updateRecruteur(Recruteur recruteur) {
        return recruteurRepository.save(recruteur);
    }

    // Delete a Recruteur by id
    public void deleteById(Integer id) {
        if (!recruteurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recruteur", "id", id);
        }
        recruteurRepository.deleteById(id);
    }
}

