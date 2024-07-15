package com.alura.ChallengeLiteratura.Principal;

import com.alura.ChallengeLiteratura.Service.ConsumoAPIGutendex;
import com.alura.ChallengeLiteratura.Service.ConvierteDatos;
import com.alura.ChallengeLiteratura.modelos.AutorDB;
import com.alura.ChallengeLiteratura.modelos.Datos;
import com.alura.ChallengeLiteratura.modelos.LibroDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPIGutendex consumoAPI = new ConsumoAPIGutendex();
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversorDatos = new ConvierteDatos();
    private List<LibroDB> libros;
    private List<AutorDB> autores;




    public void mostrarMenuOpciones() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Lista de libros registrados
                    3 - Lista de autores registrados
                    4 - Lista de autores vivos en un determinado año
                    0 - Salir
                    """;
            System.out.println(menu);
            System.out.println("Por favor ingrese la opción deseada");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;

                case 0:
                    System.out.println("Gracias por usar la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Datos getDatos() {
        System.out.println("Escriba el título del libro: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatosLibros(URL_BASE + nombreLibro.replace(" ", "%20"));
        Datos datos = conversorDatos.obtenerDatosLibros(json, Datos.class);
        return datos;
    }

    private void buscarLibroPorTitulo() {
        Datos datos = getDatos();
        if (datos != null && !datos.resultados().isEmpty()) {
            DatosLibros primerLibro = datos.resultados().get(0);

            LibroDB libro = new LibroDB(primerLibro);

            Optional<LibroDB> libroExiste = libroRepository.findByTitulo(libro.getTitulo());
            if (libroExiste.isPresent()) {
                System.out.println("\nEl libro ya está registrado\n");
            } else {

                if (!primerLibro.autor().isEmpty()) {
                    DatosAutor autor = primerLibro.autor().get(0);
                    AutorDB autor1 = new AutorDB(autor);
                    Optional<AutorDB> autorOptional = autorRepository.findByNombre(autor1.getNombre());

                    if (autorOptional.isPresent()) {
                        AutorDB autorExiste = autorOptional.get();
                        libro.setAutor(autorExiste);
                        libroRepository.save(libro);
                    } else {
                        AutorDB autorNuevo = autorRepository.save(autor1);
                        libro.setAutor(autorNuevo);
                        libroRepository.save(libro);
                    }

                    Integer numeroDescargas = libro.getNumeroDeDescargas() != null ? libro.getNumeroDeDescargas() : 0;
                    System.out.println("********** Libro **********");
                    System.out.printf("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargas: %s%n",
                            libro.getTitulo(), autor1.getNombre(), libro.getIdioma(), libro.getNumeroDeDescargas());
                    System.out.println("***************************\n");
                } else {
                    System.out.println("Sin autor");
                }
            }
        } else {
            System.out.println("libro no encontrado");
        }
    }

    private void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para listar los autores que estaban vivos: ");
        var anio = teclado.nextInt();
        autores = autorRepository.listaAutoresVivos(anio);
        autores.stream()
                .forEach(System.out::println);
    }

}
