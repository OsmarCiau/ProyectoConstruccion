package Proyect.Controllers;

import Proyect.Authentication.AuthenticationService;
import Proyect.Authentication.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AdministratorAuthContoller {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        try {
            String token = authenticationService.authenticate(
                    credentials.get("name"),
                    credentials.get("password")
            );
            return ResponseEntity.ok(Map.of("token", token, "user", credentials.get("name")));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/renew")
    public ResponseEntity<?> renewToken(@RequestHeader("x-token") String token) {
        try {
            String newToken = authenticationService.renewToken(token);
            return ResponseEntity.ok(newToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerAdministrator(@RequestBody Administrator newAdmin) {
        try {
            authenticationService.registerAdministrator(newAdmin);
            return new ResponseEntity<>("Administrador creado exitosamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/admins")
    public ResponseEntity<Iterable<Administrator>> getAllAdministrators() {
        Iterable<Administrator> admins = authenticationService.getAllAdministrators();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }


    @DeleteMapping("/admins/{id}")
    public ResponseEntity<String> deleteAdministrator(@PathVariable Long id) {
        try {
            authenticationService.deleteAdministrator(id);
            return new ResponseEntity<>("Administrador eliminado", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

