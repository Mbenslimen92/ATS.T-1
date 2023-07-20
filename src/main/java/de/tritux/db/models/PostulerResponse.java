package de.tritux.db.models;

import de.tritux.db.entities.Candidature;
import lombok.Data;
@Data
public class PostulerResponse {
    private Candidature candidature;
    private String message;

    public PostulerResponse(Candidature candidature, String message) {
        this.candidature = candidature;
        this.message = message;
    }

    
}
