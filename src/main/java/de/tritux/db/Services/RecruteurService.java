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

    public List<Recruteur> getAllRecruteur() {
        return recruteurRepository.findAll();
    }

    public Recruteur saveRecruteur(Recruteur recruteur) {
        return recruteurRepository.save(recruteur);
    }

    public Recruteur getOne(Integer id) {
        return recruteurRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Recruteur", "id", id));
    }

    public Recruteur updateRecruteur(Recruteur recruteur) {
        return recruteurRepository.save(recruteur);
    }

    public void deleteById(Integer id) {
        if (!recruteurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recruteur", "id", id);
        }
        recruteurRepository.deleteById(id);
    }
}

