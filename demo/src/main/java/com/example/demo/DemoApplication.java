// Questo è il package principale dell'applicazione
package com.example.demo;

// Importiamo le classi necessarie da Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Questa annotazione indica che questa è un'applicazione Spring Boot
// Combina 3 annotazioni: @Configuration, @EnableAutoConfiguration e @ComponentScan
@SpringBootApplication
public class DemoApplication {

	// Questo è il metodo main, punto di ingresso dell'applicazione
	// Viene eseguito quando avviamo l'applicazione
	public static void main(String[] args) {
		// Questo avvia l'applicazione Spring Boot
		// Configura automaticamente l'applicazione in base alle dipendenze
		// e avvia il server web incorporato
		SpringApplication.run(DemoApplication.class, args);
	}

}
