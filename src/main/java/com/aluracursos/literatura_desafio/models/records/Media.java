package com.aluracursos.literatura_desafio.models.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Media(
        @JsonAlias("image/jpeg") String imagen
        // Mapea el campo "image/jpeg" del JSON a la variable `imagen`.
) {
}
