package com.example.demo.controllers;

import com.example.demo.entities.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Questa classe gestisce le richieste HTTP relative agli autori
@RestController // Indica che questa classe è un controller REST
@RequestMapping("/authors") // Tutte le richieste a /authors verranno gestite da questo controller
public class AuthorController {
    
    @Autowired // Inietta automaticamente un'istanza di AuthorRepository
    private AuthorRepository authorRepository;

    // Gestisce le richieste GET a /authors e restituisce tutti gli autori
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll(); // Recupera tutti gli autori dal database
    }

    // Gestisce le richieste GET a /authors/{id} e restituisce un autore specifico
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        return authorRepository.findById(id) // Cerca l'autore per ID
                .map(ResponseEntity::ok) // Se trovato, restituisce 200 OK con l'autore
                .orElse(ResponseEntity.notFound().build()); // Se non trovato, restituisce 404 Not Found
    }

    // Gestisce le richieste POST a /authors per creare un nuovo autore
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author); // Salva il nuovo autore nel database
    }

    // Gestisce le richieste PUT a /authors/{id} per aggiornare un autore esistente
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        if (!authorRepository.existsById(id)) { // Verifica se l'autore esiste
            return ResponseEntity.notFound().build(); // Se non esiste, restituisce 404 Not Found
        }
        author.setId(id); // Imposta l'ID dell'autore da aggiornare
        return ResponseEntity.ok(authorRepository.save(author)); // Salva e restituisce l'autore aggiornato
    }

    // Gestisce le richieste DELETE a /authors/{id} per eliminare un autore
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (!authorRepository.existsById(id)) { // Verifica se l'autore esiste
            return ResponseEntity.notFound().build(); // Se non esiste, restituisce 404 Not Found
        }
        authorRepository.deleteById(id); // Elimina l'autore dal database
        return ResponseEntity.ok().build(); // Restituisce 200 OK
    }
} 