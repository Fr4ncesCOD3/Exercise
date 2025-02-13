package com.example.demo.controllers;

import com.example.demo.entities.Author;
import com.example.demo.exceptions.AuthenticationException;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.RegisterRequest;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/register")
    public ResponseEntity<Author> register(@RequestBody RegisterRequest request) {
        // Validazione dei campi obbligatori
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome è obbligatorio");
        }
        if (request.getCognome() == null || request.getCognome().trim().isEmpty()) {
            throw new IllegalArgumentException("Il cognome è obbligatorio");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("L'email è obbligatoria");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("La password è obbligatoria");
        }

        // Verifica se l'email è già in uso
        if (authorRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email già in uso");
        }

        Author author = new Author();
        author.setNome(request.getNome().trim());
        author.setCognome(request.getCognome().trim());
        author.setEmail(request.getEmail().trim());
        author.setPassword(request.getPassword());
        author.setDataDiNascita(request.getDataDiNascita());

        return ResponseEntity.ok(authorRepository.save(author));
    }

    @PostMapping("/login")
    public ResponseEntity<Author> login(@RequestBody LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("L'email è obbligatoria");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("La password è obbligatoria");
        }

        Author author = authorRepository.findByEmailAndPassword(
                request.getEmail().trim(), 
                request.getPassword()
        ).orElseThrow(() -> new AuthenticationException("Credenziali non valide"));
        
        return ResponseEntity.ok(author);
    }
} 