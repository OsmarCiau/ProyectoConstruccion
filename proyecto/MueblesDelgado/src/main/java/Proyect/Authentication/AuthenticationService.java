package Proyect.Authentication;

import Proyect.Repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AdministratorRepository administratorRepository;

//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticate(String name, String password) {
        Administrator admin = administratorRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//        if (!passwordEncoder.matches(password, admin.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }

        return JwtUtil.generateToken(admin.getName());
    }

    public Administrator validateToken(String token) {
        if (!JwtUtil.validateToken(token)) {
            throw new RuntimeException("Token inválido");
        }

        String name = JwtUtil.getSubjectFromToken(token);
        return administratorRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    public String renewToken(String token) {
        Administrator admin = validateToken(token);

        return JwtUtil.generateToken(admin.getName());
    }

    public Administrator registerAdministrator(Administrator newAdmin) {
        if (administratorRepository.existsByName(newAdmin.getName())) {
            throw new RuntimeException("El administrador ya existe");
        }

//        newAdmin.setPassword(passwordEncoder.encode(newAdmin.getPassword()));
        newAdmin.setPassword(newAdmin.getPassword());

        return administratorRepository.save(newAdmin);
    }

    public Iterable<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    public void deleteAdministrator(Long id) {
        Optional<Administrator> admin = administratorRepository.findById(id);

        if (admin.isEmpty()) {
            throw new RuntimeException("Administrador no encontrado");
        }

        administratorRepository.deleteById(id);
    }
}