package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    // Costruttori
    public Author() {
        this.avatar = generateAvatar();
    }

    // Metodo per generare l'avatar
    private String generateAvatar() {
        return "https://ui-avatars.com/api/?name=" + 
               (nome != null ? nome : "") + "+" + 
               (cognome != null ? cognome : "");
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.avatar = generateAvatar();
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
        this.avatar = generateAvatar();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getAvatar() {
        return avatar;
    }
} 