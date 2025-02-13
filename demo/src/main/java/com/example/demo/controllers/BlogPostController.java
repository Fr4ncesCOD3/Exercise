package com.example.demo.controllers;

import com.example.demo.entities.Author;
import com.example.demo.entities.BlogPost;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.payload.BlogPostPayload;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controller per gestire le operazioni CRUD dei post del blog
@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {
    
    @Autowired
    private BlogPostRepository blogPostRepository;
    
    @Autowired
    private AuthorRepository authorRepository;

    // Restituisce tutti i post con paginazione
    @GetMapping
    public Page<BlogPost> getAllPosts(@PageableDefault(size = 10, sort = "dataCreazione") Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    // Restituisce un singolo post per ID
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post non trovato con id: " + id)));
    }

    // Crea un nuovo post
    @PostMapping
    public BlogPost createPost(@RequestBody BlogPostPayload payload) {
        Author author = authorRepository.findById(payload.getAutoreId())
                .orElseThrow(() -> new NotFoundException("Autore non trovato con id: " + payload.getAutoreId()));
        
        // Verifica che l'autore sia autenticato (in produzione usare Spring Security)
        if (author == null) {
            throw new IllegalArgumentException("Devi essere autenticato per creare un post");
        }
        
        BlogPost blogPost = new BlogPost();
        blogPost.setCategoria(payload.getCategoria());
        blogPost.setTitolo(payload.getTitolo());
        blogPost.setContenuto(payload.getContenuto());
        blogPost.setTempoDiLettura(payload.getTempoDiLettura());
        blogPost.setAutore(author);
        
        if (payload.getCover() != null) {
            blogPost.setCover(payload.getCover());
        }
        
        return blogPostRepository.save(blogPost);
    }

    // Aggiorna un post esistente
    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPostPayload payload) {
        BlogPost existingPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post non trovato con id: " + id));
        
        Author author = authorRepository.findById(payload.getAutoreId())
                .orElseThrow(() -> new NotFoundException("Autore non trovato con id: " + payload.getAutoreId()));
        
        existingPost.setCategoria(payload.getCategoria());
        existingPost.setTitolo(payload.getTitolo());
        existingPost.setContenuto(payload.getContenuto());
        existingPost.setTempoDiLettura(payload.getTempoDiLettura());
        existingPost.setAutore(author);
        
        if (payload.getCover() != null) {
            existingPost.setCover(payload.getCover());
        }
        
        return ResponseEntity.ok(blogPostRepository.save(existingPost));
    }

    // Elimina un post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new NotFoundException("Post non trovato con id: " + id);
        }
        blogPostRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 