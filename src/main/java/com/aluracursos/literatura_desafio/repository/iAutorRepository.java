package com.aluracursos.literatura_desafio.repository;

import com.aluracursos.literatura_desafio.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iAutorRepository extends JpaRepository<Autor, Long> {
    // Extiende JpaRepository, lo que proporciona métodos CRUD para la entidad Autor.
    // Utiliza `Long` como tipo de clave primaria.

    List<Autor> findAll();
    // Recupera todos los autores de la base de datos.

    List<Autor> findByCumpleaniosLessThanOrFechaFallecimientoGreaterThanEqual(int anioBuscado, int anioBuscado1);
    // Busca autores que estuvieron vivos en un año específico.
    // Retorna aquellos cuya fecha de nacimiento es menor o cuya fecha de fallecimiento es mayor o igual al año dado.


    Optional<Autor> findFirstByNombreContainsIgnoreCase(String escritor);
    // Busca el primer autor cuyo nombre contenga la cadena proporcionada, ignorando mayúsculas y minúsculas.
    // Usa `Optional<Autor>` para manejar posibles valores `null` de manera segura.

}
