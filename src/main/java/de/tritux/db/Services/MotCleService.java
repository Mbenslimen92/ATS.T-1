package de.tritux.db.Services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import de.tritux.db.Exception.NotFoundException;
import de.tritux.db.MotCle.MotCle;
import de.tritux.db.entities.Emploi;
import de.tritux.db.repositories.EmploiRepository;
import de.tritux.db.repositories.MotCleRepository;

@Service
public class MotCleService {

	private final MotCleRepository motCleRepository;
    private final EmploiRepository emploiRepository;

    public MotCleService(MotCleRepository motCleRepository, EmploiRepository emploiRepository) {
        this.motCleRepository = motCleRepository;
        this.emploiRepository = emploiRepository;
    }

    public MotCle createMotCleWithEmploiId(Integer emploiId, String valeurMC) {
        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new NotFoundException("Emploi introuvable"));

        MotCle motCle = new MotCle();
        motCle.setValeurMC(valeurMC);
        motCle.setEmplois(Collections.singletonList(emploi));

        return motCleRepository.save(motCle);
    }
    public List<MotCle> getAllMotsCles() {
        return motCleRepository.findAll();
    }

    public MotCle getMotCleById(Integer motCleId) {
        return motCleRepository.findById(motCleId)
                .orElseThrow();
    }

    public void deleteMotCle(Integer motCleId) {
        motCleRepository.deleteById(motCleId);
    }
}
