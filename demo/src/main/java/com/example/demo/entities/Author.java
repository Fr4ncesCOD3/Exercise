// Questo è il package che contiene tutte le entità (classi che rappresentano tabelle nel database)
package com.example.demo.entities;

// Importiamo le annotazioni necessarie per JPA (Java Persistence API) e la classe LocalDate
import jakarta.persistence.*;
import java.time.LocalDate;

// @Entity indica che questa classe è mappata a una tabella nel database
@Entity
public class Author {
    // @Id indica che questo campo è la chiave primaria
    // @GeneratedValue specifica che l'ID viene generato automaticamente dal database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Questi sono i campi base che descrivono un autore
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cognome;
    
    @Column(nullable = false)
    private String email;
    
    private LocalDate dataDiNascita;
    private String avatar;
    
    @Column(nullable = false)
    private String password;

    // Costruttore di default che viene chiamato quando creiamo un nuovo autore
    // Inizializza l'avatar usando il metodo generateAvatar
    public Author() {
        this.avatar = generateAvatar();
    }

    // Questo metodo privato genera un URL per l'avatar usando il servizio ui-avatars.com
    // Combina nome e cognome per creare un avatar personalizzato
    private String generateAvatar() {
        return "https://ui-avatars.com/api/?name=" + 
               (nome != null ? nome : "") + "+" + // Se nome è null, usa stringa vuota
               (cognome != null ? cognome : "");  // Se cognome è null, usa stringa vuota
    }

    // I seguenti sono metodi getter e setter che permettono di accedere e modificare i campi privati
    // Getter per l'ID
    public Long getId() {
        return id;
    }

    // Setter per l'ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter per il nome
    public String getNome() {
        return nome;
    }

    // Setter per il nome - aggiorna anche l'avatar quando il nome cambia
    public void setNome(String nome) {
        this.nome = nome;
        this.avatar = generateAvatar();
    }

    // Getter per il cognome
    public String getCognome() {
        return cognome;
    }

    // Setter per il cognome - aggiorna anche l'avatar quando il cognome cambia
    public void setCognome(String cognome) {
        this.cognome = cognome;
        this.avatar = generateAvatar();
    }

    // Getter per l'email
    public String getEmail() {
        return email;
    }

    // Setter per l'email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter per la data di nascita
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    // Setter per la data di nascita
    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    // Getter per l'avatar - nota che non c'è un setter perché l'avatar viene generato automaticamente
    public String getAvatar() {
        return avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
} 