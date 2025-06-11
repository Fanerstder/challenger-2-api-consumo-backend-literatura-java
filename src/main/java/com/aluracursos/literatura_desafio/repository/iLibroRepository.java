package com.aluracursos.literatura_desafio.repository;

import com.aluracursos.literatura_desafio.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface iLibroRepository extends JpaRepository<Libro, Long> {

    // Extiende JpaRepository, proporcionando métodos CRUD (Crear, Leer, Actualizar, Eliminar) automáticamente.
    // Se trabaja con la entidad `Libro` y su clave primaria es de tipo `Long`.
    boolean existsByTitulo(String titulo);
    // Verifica si existe un libro con el título dado en la base de datos.
    // Retorna `true` si el libro existe, `false` si no.


    Libro findByTituloContainsIgnoreCase(String titulo);
    // Busca un libro cuyo título contenga la cadena `titulo`, ignorando mayúsculas y minúsculas.
    // Retorna el primer libro que coincida.

    List<Libro> findByIdioma(String idioma);
    // Recupera todos los libros que coincidan con el idioma especificado.


    @Query("SELECT l FROM Libro l ORDER BY l.numeroDeDescargas DESC LIMIT 10")
    List<Libro> findTop10ByTituloByNumeroDeDescargas();
    // Ejecuta una consulta personalizada en JPQL.
    // Obtiene los 10 libros más descargados, ordenándolos en orden descendente.



}
