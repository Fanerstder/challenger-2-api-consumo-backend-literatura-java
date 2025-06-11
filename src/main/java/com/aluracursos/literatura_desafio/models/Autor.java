package com.aluracursos.literatura_desafio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity // Marca esta clase como una entidad JPA, lo que significa que se mapeará a una tabla en la base de datos.
@Table(name = "autores")  // Especifica el nombre de la tabla en la base de datos.


public class Autor {
    @Id  // Define `id` como la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incrementa el valor del ID en la base de datos.
    private Long id;

    private String nombre;  // Almacena el nombre del autor.

    private Integer cumpleanios; // Año de nacimiento del autor.

    private Integer fechaFallecimiento;  // Año de fallecimiento del autor.

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Relación uno-a-muchos con la entidad `Libro`, indicando que un autor puede tener múltiples libros.
    // `cascade = CascadeType.ALL` asegura que los libros relacionados se guarden o eliminen junto con el autor.
    // `fetch = FetchType.LAZY` optimiza la carga de libros, evitando que se recuperen hasta que sea necesario.
    private List<Libro> libros;


    // Constructor vacío requerido por JPA.
    public Autor() {
    }

    // Constructor que recibe un objeto `Autor` del modelo `records` y asigna sus valores.
    public Autor(com.aluracursos.literatura_desafio.models.records.Autor autor) {
        this.nombre = autor.nombre();
        this.cumpleanios = autor.fechaDeNacimiento();
        this.fechaFallecimiento = autor.fechaFallecimiento();
    }

    // Método `toString()` para imprimir información del autor de manera legible
    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                        ", cumpleanios=" + cumpleanios +
                        ", fechaFallecimiento=" + fechaFallecimiento;
    }

    // Métodos getter y setter para acceder y modificar los atributos del autor.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCumpleanios() {
        return cumpleanios;
    }

    public void setCumpleanios(Integer cumpleanios) {
        this.cumpleanios = cumpleanios;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
