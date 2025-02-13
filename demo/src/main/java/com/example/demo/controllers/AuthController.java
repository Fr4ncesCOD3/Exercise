package com.example.demo.controllers;

import com.example.demo.entities.Author;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.RegisterRequest;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.exceptions.NotFoundException;
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
        // Verifica se l'email è già in uso
        if (authorRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email già in uso");
        }

        Author author = new Author();
        author.setNome(request.getNome());
        author.setCognome(request.getCognome());
        author.setEmail(request.getEmail());
        author.setPassword(request.getPassword()); // In produzione, la password dovrebbe essere criptata
        author.setDataDiNascita(request.getDataDiNascita());

        return ResponseEntity.ok(authorRepository.save(author));
    }

    @PostMapping("/login")
    public ResponseEntity<Author> login(@RequestBody LoginRequest request) {
        Author author = authorRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new NotFoundException("Credenziali non valide"));
        
        return ResponseEntity.ok(author);
    }
} 