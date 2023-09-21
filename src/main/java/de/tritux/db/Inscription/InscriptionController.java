package de.tritux.db.Inscription;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tritux.db.Exception.UserAlreadyExistsException;
import de.tritux.db.entities.User;
@RestController
@RequestMapping("/inscription")
public class InscriptionController {

    private final UserInscriptionService userInscriptionService;

    public InscriptionController(UserInscriptionService userInscriptionService) {
        this.userInscriptionService = userInscriptionService;
    }

    @PostMapping("/reg")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            boolean registrationSuccess = userInscriptionService.register(user);

            if (registrationSuccess) {
                return ResponseEntity.ok("Inscription réussie !");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'inscription a échoué. Veuillez réessayer.");
            }
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody User user) {
        try {
            boolean validationSuccess = userInscriptionService.validateRegistration(user);

            if (validationSuccess) {
                return ResponseEntity.ok("Validation réussie !");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La validation a échoué. Veuillez réessayer.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la validation.");
        }
    }
}
