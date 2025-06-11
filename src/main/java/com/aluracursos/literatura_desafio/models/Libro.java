package com.aluracursos.literatura_desafio.models;

import com.aluracursos.literatura_desafio.dto.Genero;

import com.aluracursos.literatura_desafio.models.records.DatosLibros;
import com.aluracursos.literatura_desafio.models.records.Media;
import jakarta.persistence.*;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity // Indica que esta clase representa una entidad en la base de datos.
@Table(name = "libros")  // Especifica el nombre de la tabla en la base de datos.
public class Libro {
    @Id // Define la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementa el ID en la base de datos.
    private Long id;

    private Long libroId; // ID específico del libro (puede venir de la API).

    @Column(unique = true) // Evita registros duplicados con el mismo título.
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Asegura que el autor se guarde automáticamente
    @JoinColumn(name = "autor_id")  // Define la clave foránea en la tabla 'libros'.

    //@Transient
    private Autor autor;  // Referencia a la entidad Autor.
    @Enumerated(EnumType.STRING)  // Guarda el género como un String en la base de datos.
    private Genero genero;
    private String idioma;  // Almacena el idioma del libro.
    private String imagen;  // URL de la imagen del libro.

    private Long numeroDeDescargas;  // Cantidad de descargas del libro.

    // Constructor vacío requerido por JPA.
    public Libro() {
    }

    // Constructor que toma datos desde la API y los mapea a la entidad.
    public Libro (DatosLibros datosLibros) {
        this.libroId = datosLibros.libroId();
        this.titulo = datosLibros.titulo();
        // Si autor es una lista de autores (como parece en tu registro DatosLibro)
        if (datosLibros.autor() != null && !datosLibros.autor().isEmpty()) {
            this.autor = new Autor(datosLibros.autor().get(0)); // Toma el primer autor de la lista
        } else {
            this.autor = null; // o maneja el caso de que no haya autor
        }
        this.genero =  generoModificado(datosLibros.genero());
        this.idioma = idiomaModificado(datosLibros.idioma());
        this.imagen = imagenModificada(datosLibros.imagen());
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }



    public Libro(Libro libro) {
    }

    // Método para modificar el género, tomando el primer elemento si existe.
    private Genero generoModificado(List<String> generos) {
        if (generos == null || generos.isEmpty()) {
            return Genero.DESCONOCIDO; // Si no hay género, usa un valor por defecto.
        }
        Optional<String> firstGenero = generos.stream()
                .map(g -> {
                    int index = g.indexOf("--");  // Extrae el valor después de "--".
                    return index != -1 ? g.substring(index + 2).trim() : null;
                })
                .filter(Objects::nonNull) // Filtra valores nulos.
                .findFirst();
        return firstGenero.map(Genero::fromString).orElse(Genero.DESCONOCIDO);
    }

    // Método para obtener el idioma (toma el primer elemento si existe).
    private String idiomaModificado(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconocido";  // Si no hay idioma, devuelve "Desconocido".
        }
        return idiomas.get(0); // Retorna el primer idioma.
    }

    // Método para obtener la imagen.
    private String imagenModificada(Media media) {
        if (media == null || media.imagen().isEmpty()) {
            return "Sin imagen";  // Si no hay imagen, usa un mensaje por defecto.
        }
        return media.imagen();  // Retorna la URL de la imagen.


    }


    // Getters y Setters para los atributos de la entidad.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) { this.imagen = imagen; }

    public Long getNumeroDeDescargas() { return numeroDeDescargas; }

    public void setNumeroDeDescargas(Long numeroDeDescargas) { this.numeroDeDescargas = numeroDeDescargas;}

    // Método `toString()` para imprimir información del libro.
    @Override
    public String toString() {
        return
                "  \nid=" + id +
                        "  \nLibro id=" + libroId +
                        ", \ntitulo='" + titulo + '\'' +
                        ", \nauthors=" + (autor != null ? autor.getNombre() : "N/A")+
                        ", \ngenero=" + genero +
                        ", \nidioma=" + idioma +
                        ", \nimagen=" + imagen +
                        ", \ncantidadDescargas=" + numeroDeDescargas;
    }
}
