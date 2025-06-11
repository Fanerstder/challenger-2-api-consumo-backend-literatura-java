package com.aluracursos.literatura_desafio;

import com.aluracursos.literatura_desafio.principal.Principal;
import com.aluracursos.literatura_desafio.repository.iAutorRepository;
import com.aluracursos.literatura_desafio.repository.iLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaDesafioApplication implements CommandLineRunner{
	// Implementa `CommandLineRunner`, permitiendo ejecutar código al inicio de la aplicación.

	@Autowired
	private iLibroRepository libroRepository;  // Inyección de dependencia para acceder al repositorio de libros.
	@Autowired
	private iAutorRepository autorRepository;  // Inyección de dependencia para acceder al repositorio de autores.


	public static void main(String[] args) {
		// Método principal: inicia la aplicación Spring Boot.
		SpringApplication.run(LiteraturaDesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Se ejecuta automáticamente después de que Spring Boot inicia la aplicación

		Principal principal = new Principal(libroRepository, autorRepository);
		// Crea una instancia de `Principal` y pasa los repositorios inyectados.

		principal.muestraMenu();  // Llama al método para mostrar el menú interactivo.


	}
}
