package com.aluracursos.literatura_desafio.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    // Clase encargada de consumir una API utilizando HttpClient de Java.

    public String obtenerDatosApi(String url) {
        // Método para realizar una solicitud HTTP y obtener una respuesta en formato String.

        HttpClient client = HttpClient.newHttpClient();
        // Crea un cliente HTTP reutilizable.

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        // Construye una solicitud HTTP con la URL proporcionada.

        HttpResponse<String> response = null;
        // Variable para almacenar la respuesta HTTP.

        try{
                    response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());
            // Envía la solicitud y recibe la respuesta como una cadena de texto.


        }catch (IOException | InterruptedException e){
                    throw new RuntimeException(e);
            // Manejo de errores en caso de problemas de conexión o interrupción.
        }
                return response.body();
        // Devuelve el cuerpo de la respuesta HTTP como String.

    }
}
