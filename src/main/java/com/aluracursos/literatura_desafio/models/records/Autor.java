package com.aluracursos.literatura_desafio.models.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Autor(
        @JsonAlias("name") String nombre,
        // Mapea el campo "name" del JSON a la variable `nombre`.

        @JsonAlias("birth_year") Integer fechaDeNacimiento,
        // Mapea el campo "birth_year" del JSON a `fechaDeNacimiento`.

        @JsonAlias("death_year") Integer fechaFallecimiento
        // Mapea el campo "death_year" del JSON a `fechaFallecimiento`.
) {

}
