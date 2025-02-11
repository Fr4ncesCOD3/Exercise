package com.example.demo.controllers;

import com.example.demo.entities.BlogPost;
import com.example.demo.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Questa classe è un controller REST che gestisce le operazioni CRUD per i post del blog
@RestController
@RequestMapping("/blogPosts") // Tutte le richieste a /blogPosts verranno gestite da questo controller
public class BlogPostController {
    
    // Inietta automaticamente un'istanza del repository per interagire con il database
    @Autowired
    private BlogPostRepository blogPostRepository;

    // Gestisce le richieste GET a /blogPosts
    // Restituisce tutti i post del blog presenti nel database
    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    // Gestisce le richieste GET a /blogPosts/{id}
    // Restituisce un singolo post in base all'ID specificato nel percorso URL
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPost(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(ResponseEntity::ok) // Se il post viene trovato, restituisce 200 OK con il post
                .orElse(ResponseEntity.notFound().build()); // Se non trovato, restituisce 404 Not Found
    }

    // Gestisce le richieste POST a /blogPosts
    // Crea un nuovo post del blog con i dati forniti nel corpo della richiesta
    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    // Gestisce le richieste PUT a /blogPosts/{id}
    // Aggiorna un post esistente identificato dall'ID nel percorso
    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        if (!blogPostRepository.existsById(id)) { // Verifica se il post esiste
            return ResponseEntity.notFound().build(); // Se non esiste, restituisce 404 Not Found
        }
        blogPost.setId(id); // Imposta l'ID del post da aggiornare
        return ResponseEntity.ok(blogPostRepository.save(blogPost)); // Salva e restituisce il post aggiornato
    }

    // Gestisce le richieste DELETE a /blogPosts/{id}
    // Elimina un post esistente identificato dall'ID nel percorso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (!blogPostRepository.existsById(id)) { // Verifica se il post esiste
            return ResponseEntity.notFound().build(); // Se non esiste, restituisce 404 Not Found
        }
        blogPostRepository.deleteById(id); // Elimina il post dal database
        return ResponseEntity.ok().build(); // Restituisce 200 OK
    }
} 