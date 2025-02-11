// Questo è il package che contiene tutte le entità del nostro progetto
package com.example.demo.entities;

// Importiamo le librerie necessarie per JPA e per gestire le date/ore
import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity indica che questa classe rappresenta una tabella nel database
@Entity
public class BlogPost {
    // @Id marca questo campo come chiave primaria
    // @GeneratedValue fa generare automaticamente gli ID dal database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Questi sono i campi base che descrivono un post del blog
    private String categoria;    // La categoria del post (es: "Tech", "Sport", ecc.)
    private String titolo;       // Il titolo del post
    private String cover;        // URL dell'immagine di copertina
    private String contenuto;    // Il contenuto testuale del post
    private int tempoDiLettura;  // Tempo stimato di lettura in minuti
    private LocalDateTime dataCreazione;  // Data e ora di creazione del post
    
    // @ManyToOne indica una relazione molti-a-uno con l'entità Author
    // Molti post possono essere scritti da uno stesso autore
    @ManyToOne
    private Author autore;

    // Costruttore di default che viene chiamato quando creiamo un nuovo post
    public BlogPost() {
        // Imposta un'immagine di default usando picsum.photos
        this.cover = "https://picsum.photos/200/300";
        // Imposta la data di creazione al momento attuale
        this.dataCreazione = LocalDateTime.now();
    }

    // I seguenti sono metodi getter e setter che permettono di accedere 
    // e modificare i campi privati della classe
    
    // Getter e Setter per l'ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter per la categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter e Setter per il titolo
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    // Getter e Setter per la cover
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    // Getter e Setter per il contenuto
    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    // Getter e Setter per il tempo di lettura
    public int getTempoDiLettura() {
        return tempoDiLettura;
    }

    public void setTempoDiLettura(int tempoDiLettura) {
        this.tempoDiLettura = tempoDiLettura;
    }

    // Getter e Setter per l'autore
    public Author getAutore() {
        return autore;
    }

    public void setAutore(Author autore) {
        this.autore = autore;
    }
} 