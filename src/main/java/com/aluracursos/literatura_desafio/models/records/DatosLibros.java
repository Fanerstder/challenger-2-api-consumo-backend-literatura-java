package com.aluracursos.literatura_desafio.models.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("id") Long libroId,
        // Mapea el campo "id" del JSON al atributo `libroId`, representando el identificador único del libro.

        @JsonAlias("title") String titulo,
        // Mapea el campo "title" del JSON al atributo `titulo`,
        @JsonAlias("languages") List<String> idioma,
        // Extrae a lista de idiomas en los que está disponible el libro desde el JSON.


@JsonAlias("subjects")  List<String> genero,
        // Obtiene una lista de géneros asociados al libro desde el JSON.

        @JsonAlias("formats") Media imagen,
        // Recupera la información de formatos disponibles del libro, incluyendo la imagen, usando la clase `Media`.

        @JsonAlias("authors") List<Autor> autor,
        // Extrae la lista de autores del libro y los mapea a la clase `Autor`.

        @JsonAlias("download_count") Long numeroDeDescargas
        // Obtiene el número total de descargas del libro desde la API.

) {

}
