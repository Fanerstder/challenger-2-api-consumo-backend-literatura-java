package com.aluracursos.literatura_desafio.config;

public interface iConvierteDatos {
    /**
     * Método genérico para convertir un String JSON en una instancia de la clase especificada.
     *
     * @param json  El JSON a convertir.
     * @param clase La clase destino para la conversión.
     * @param <T>   Tipo genérico que permite flexibilidad en la conversión.
     * @return      Una instancia de la clase especificada con los datos mapeados desde el JSON.
     */


    <T> T convertirDatosJsonAJava(String json, Class<T> clase);
}
