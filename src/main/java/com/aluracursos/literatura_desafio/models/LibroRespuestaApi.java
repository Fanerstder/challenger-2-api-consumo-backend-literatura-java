package com.aluracursos.literatura_desafio.models;

import com.aluracursos.literatura_desafio.models.records.DatosLibros;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroRespuestaApi {
    // Clase utilizada para mapear la respuesta JSON de la API Gutendex.

    @JsonAlias("results")
    List<DatosLibros> resultadosLibros;
    // Mapea el atributo "results" del JSON a la lista de objetos `DatosLibros`.

    public List<DatosLibros> getResultadosLibros() {
        return resultadosLibros;
    }
    // Getter para obtener la lista de libros dentro de la respuesta.


    public void setResultadosLibros(List<DatosLibros> resultadosLibros) {
        this.resultadosLibros = resultadosLibros;
    }
    // Setter para modificar la lista de libros en caso necesario.


}
