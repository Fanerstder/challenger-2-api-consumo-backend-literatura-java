package com.aluracursos.literatura_desafio.principal;

import com.aluracursos.literatura_desafio.config.ConvierteDatos;
import com.aluracursos.literatura_desafio.models.Autor;
import com.aluracursos.literatura_desafio.models.Libro;
import com.aluracursos.literatura_desafio.models.LibroRespuestaApi;
import com.aluracursos.literatura_desafio.models.records.DatosLibros;
import com.aluracursos.literatura_desafio.repository.iAutorRepository;
import com.aluracursos.literatura_desafio.repository.iLibroRepository;
import com.aluracursos.literatura_desafio.service.ConsumoAPI;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private final String URL_Base = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    //private List<Libro> datosLibro = new ArrayList<>();
    private iLibroRepository libroRepository;
    private iAutorRepository autorRepository;

    // Constructor: Inicializa los repositorios de libros y autores
    public Principal(iLibroRepository libroRepository, iAutorRepository autorRepository){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // Método para mostrar el menú de opciones al usuario
    public void muestraMenu() {
        System.out.println("DESAFIO DE LITERATURA");

        var opcion = -1;
        while (opcion !=0){
            var menu = """
                    ****************************************
                    *****  BIENVENIDOS A GLOBAL BOOKS  *****
                    ****************************************
                    
                    1 - Agregar libros por Nombre
                    2 - Listar libros registrados
                    3 - listar autores Registrados
                    4 - lista Autores vivo en un determinado año
                    5 - listar libros por idioma
                    6 - Buscar libros por nombres
                    7 - Top 10 libros buscados
                    8 - Buscar autor por nombre
                    
                    0 - Salir
                    
                    ****************************************
                    *****      INGRESE UNA OPCIÓN     ******
                    ****************************************
                    """;

            try{
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpia el buffer después de la entrada numérica
            } catch (InputMismatchException e) {
                System.out.println("Por favor,  Ingrese un número válido.");
                teclado.nextLine(); // Evita el bucle infinito por entrada inválida
                continue;
            }

            // Se usa un switch para manejar las opciones del menú
            switch (opcion){
                case 1:
                    buscarLibroEnLaWeb();
                    break;
                case 2:
                    librosBuscados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdiomas();
                    break;
                case 6:
                    buscarLibrosPorNombres();
                    break;
                case 7:
                    top10LibrosBuscados();
                    break;
                case 8:
                    buscarAutorPorNombre();
                    break;

                case 0:
                    opcion = 0;
                    System.out.println("|********************************|");
                    System.out.println("|    Aplicación cerrada. Bye!    |");
                    System.out.println("|********************************|\n");
                    break;
                default:
                    System.out.println("|*********************|");
                    System.out.println("|  Opción Incorrecta. |");
                    System.out.println("|*********************|\n");
                    System.out.println("Intente con una nueva Opción");
                    muestraMenu();  // Volver a mostrar el menú en caso de opción incorrecta
                    break;
            }
        }
    }

    // Método para obtener datos de un libro desde la API Gutendex
    private Libro getDataLibro () {
        System.out.println("Escriba el titulo del libro");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatosApi(URL_Base + tituloLibro.replace(" ", "%20"));
        LibroRespuestaApi datos = conversor.convertirDatosJsonAJava(json, LibroRespuestaApi.class);

        if (datos != null && datos.getResultadosLibros() != null && !datos.getResultadosLibros().isEmpty()) {

            // Obtiene el primer libro de la lista de resultados
            DatosLibros primerLibro = datos.getResultadosLibros().get(0);
            return new Libro(primerLibro);
        }
        else {
            System.out.println("No se encontro resultados");
            return null;
        }
    }

    // Método para buscar un libro en la API y guardarlo en la base de datos
    private void buscarLibroEnLaWeb(){
        Libro libro = getDataLibro();

        if (libro == null){
            System.out.println("Libro no encontrado.el valor es null");
            return;
        }

        try{
            // Verifica si el libro ya existe en la base de datos
            boolean libroExistes = libroRepository.existsByTitulo(libro.getTitulo());
            if (libroExistes ) {
                System.out.println("El libro ya existe en la base de datos");
            } else {
                libroRepository.save(libro); // Guarda el libro en la base de datos
                System.out.println(libro.toString());
            }
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println("No se pudo persistir el libro buscado");
        }
    }

    // Método para listar libros almacenados en la base de datos
    @Transactional(readOnly = true)
    private void librosBuscados(){
        //datosLibro.forEach(System.out::println);
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println("Libros encontrados en la base de datos:");
//            for (Libro libro : libros) {
//                System.out.println(libro.toString());
//            }
            libros.forEach(System.out::println); // Muestra cada libro en la lista
            System.out.println("----------------------------------------------------------");
        }
    }

    // Método para listar autores registrados en la base de datos
    private  void listarAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No se encontro autores en la base de datos");
            
        } else {
            System.out.println("Autores encontrado en la base de datos");
            Set<String> unicoAutor = new HashSet<>();
            for (Autor autor: autores){
                if (unicoAutor.add(autor.getNombre())){
                    System.out.println(autor.getNombre()+'\n');
                }
            }

        }
    }

    // Método para listar autores vivos en un año específico
    private void listarAutoresVivosPorAnio(){
        System.out.println("Digite el año en que deseas buscar autores");
        var aniosBuscado = teclado.nextInt();
        teclado.nextLine(); // Limpia el buffer

        List<Autor> autoresVivos = autorRepository.findByCumpleaniosLessThanOrFechaFallecimientoGreaterThanEqual(aniosBuscado,  aniosBuscado);
        if (autoresVivos.isEmpty()){
            System.out.println("No se encontraron autores para este año: " + aniosBuscado);
        }else{
            System.out.println("Lo autores que estan vivo para este año " + aniosBuscado + ", Son: ");
            Set<String> autoresUnico = new HashSet<>();

//            for (Autor autor: autoresVivos){
//                if (autor.getCumpleanios() != null && autor.getFechaFallecimiento() != null) {
//
//                    if (autor.getCumpleanios() <= aniosBuscado && autor.getFechaFallecimiento() >= aniosBuscado){
//
//                        if (autoresUnico.add(autor.getNombre())){
//                            System.out.println("autor: " + autor.getNombre());
//                        }
//                    }
//
//                }
//
//            }
            autoresVivos.stream()
                    .map(Autor::getNombre)
                    .distinct()
                    .forEach(System.out::println);

        }
    }

    // Método para listar libros por idiomas
    private void listarLibrosPorIdiomas(){
        System.out.println("***************************************************");
        System.out.println("*****           Selecione un idioma           *****");
        System.out.println("---------------------------------------------------");


        var subMenu = """
                es - Idioma español
                en - Idioma ingles
                fr - Idioma Frances
                """;
        System.out.println(subMenu);
        var selectIdioma = teclado.nextLine();
        List<Libro> librosPorIdiomas = libroRepository.findByIdioma(selectIdioma);

        if (librosPorIdiomas.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos");
        } else {
            System.out.println("Libros encontrados en la base de datos:");
            for (Libro libro: librosPorIdiomas){
                System.out.println(libro.toString());
            }
        }
    }

    // Método para buscar libros por nombres
    private void buscarLibrosPorNombres(){
        System.out.println("Nombre del libro que deseas buscar");
        var titulo = teclado.nextLine();
        Libro libroBuscado = libroRepository.findByTituloContainsIgnoreCase(titulo);

        if (libroBuscado != null) {
            System.out.println("El libro buscado fue: " + libroBuscado);
        } else {
            System.out.println("El libro con el titulo: '" + titulo + "' No fue encontrado");
        }
    }

    // Método para mostrar lo 10 libros mas descargados
    private void top10LibrosBuscados(){
        List<Libro> top10Libros = libroRepository.findTop10ByTituloByNumeroDeDescargas();
        if (!top10Libros.isEmpty()) {
            int index = 1;
            for (Libro libro: top10Libros){
                System.out.println("----------------------------------------------------------------------");
                System.out.printf("Libro %d: %s - Autor: %s - Descarga: %d\n",
                        index, libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getNumeroDeDescargas());
                index++;
               // System.out.println("----------------------------------------------------------------------");
            }
        }
    }

    //Método para buscar autor por nombre
    private void buscarAutorPorNombre(){
        System.out.println("Nombre del autor que deseas buscar");
        var nombreAutor = teclado.nextLine();
        Optional<Autor> escritorBuscado = autorRepository.findFirstByNombreContainsIgnoreCase(nombreAutor);

        if (escritorBuscado.isPresent()){
            System.out.println("******   El escritor buscado fue: " + escritorBuscado.get().getNombre() + "\n");
        } else {
            System.out.println(" El escritor con el nombre: '" + nombreAutor + "' no se encontro");
        }
    }

}
