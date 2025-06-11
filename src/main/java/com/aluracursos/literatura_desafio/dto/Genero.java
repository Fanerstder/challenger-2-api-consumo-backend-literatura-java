package com.aluracursos.literatura_desafio.dto;

public enum Genero {
    // Define una enumeración (enum) para representar distintos géneros literarios.

    ACCION ("Action"),
    ROMANCE ("Romance"),
    CRIMEN ("Crime"),
    COMEDIA ("Comedy"),
    DRAMA ("Drama"),
    AVENTURA ("Adventure"),
    FICCION ("Fiction"),
    DESCONOCIDO("Desconocido");
    // Lista de géneros disponibles, incluyendo un valor `DESCONOCIDO` para casos no reconocidos.

    private String genero;
    // Almacena la versión en inglés del género, utilizada en la API de Gutendex.

    Genero(String generoGutendex) {
        this.genero = generoGutendex;
    }

    // Constructor privado que asigna la versión en inglés al campo `genero`.
    public static Genero fromString(String text){
        // Método estático para convertir un texto en un valor `Genero` válido.
        for (Genero generoEnum: Genero.values()){
            if (generoEnum.genero.equals(text)){
                return generoEnum;
            }
        }
        return Genero.DESCONOCIDO;
    }
    // Itera sobre todos los valores del enum y verifica si `text` coincide con alguno.
    // Si no encuentra un valor válido, devuelve `DESCONOCIDO`.
}
