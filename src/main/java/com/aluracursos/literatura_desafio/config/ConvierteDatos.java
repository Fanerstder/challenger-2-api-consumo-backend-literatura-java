package com.aluracursos.literatura_desafio.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements iConvierteDatos{

    private ObjectMapper mapper = new ObjectMapper();
    // Instancia de ObjectMapper (de la biblioteca Jackson) para convertir JSON en objetos Java.

    @Override
    public <T> T convertirDatosJsonAJava(String json, Class<T> clase){
        try{
            return mapper.readValue(json, clase);
            // Convierte el string JSON en una instancia de la clase especificada.
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
            // Lanza una excepción en caso de error en la conversión de JSON.
        }
    }

}
