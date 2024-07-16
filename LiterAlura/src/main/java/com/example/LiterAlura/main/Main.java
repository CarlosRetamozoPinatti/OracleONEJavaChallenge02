package com.example.LiterAlura.main;

import com.example.LiterAlura.model.Author;
import com.example.LiterAlura.model.Book;
import com.example.LiterAlura.model.Language;
import com.example.LiterAlura.repository.AuthorRepository;
import com.example.LiterAlura.repository.BookRepository;
import com.example.LiterAlura.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    private Scanner scanner;
    private APIRequest apiRequest;
    private DataConversion dataConversion;
    private static final String URL_BASE = "https://gutendex.com/books/";
    private List<Book> book;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private DataStatisticsService dataStatisticsService;

    //INICIANDO COMPONENTES
    @PostConstruct
    public void init(){
        scanner = new Scanner(System.in);
        apiRequest = new APIRequest();
        dataConversion = new DataConversion();
    }

    //MENU USUARIO
    public void showMenu(){
        var option = -1;
        while (option != 0){
            var menu = """
                    \nElija el número de la opción deseada:\n
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Listar libros por título
                    7 - Listar autores por nombre
                    8 - Buscar los 5 libros más descargados
                    9 - Mostrar estadisticas de la base de datos

                    0 - Salir
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    findByTitle();
                    break;
                case 2:
                    registeredBooks();
                    break;
                case 3:
                    registeredAuthors();
                    break;
                case 4:
                    aliveAuthors();
                    break;
                case 5:
                    findByLang();
                    break;
                case 6:
                    findByTitleDB();
                    break;
                case 7:
                    authorsByName();
                    break;
                case 8:
                    top5Books();
                    break;
                case 9:
                    showStatistics();
                    break;
                case 0:
                    System.out.println("Adios!!");
                    break;
                default:
                    System.out.println("Opcion Invalida");
            }
        }
    }

    //BUSQUEDA POR TITULO DE LIBRO
    public void findByTitle(){
        System.out.println("Ingrese el titulo del libro: ");
        String title = scanner.nextLine();
        String json = apiRequest.getData(URL_BASE + "?search=" + title.replace(" ", "+").toLowerCase());
        bookService.searchByTitle(title, json);
    }

    //LIBROS REGISTRADOS EN LA BDD
    private void registeredBooks(){
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    //AUTORES REGISTRADOS EN LA BDD
    private void registeredAuthors(){
        List<String> authors = authorService.listAllAuthors();
        System.out.println("\nAutores Registrados:\n");
        authors.forEach(System.out::println);
    }

    //AUTORES VIVOS EN LA BDD
    private void aliveAuthors(){
        System.out.println("\n¿Autores vivos en qué año desea encontrar?");
        int year = scanner.nextInt();
        authorService.listAliveAuthors(year);
    }

    //LIBROS POR IDIOMA
    private void findByLang(){
        System.out.println("¿En qué idioma desea buscar?");
        String lang = scanner.nextLine().toLowerCase();
        lang = bookService.removeTilde(lang);

        if ("español".equalsIgnoreCase(lang)) {
            lang = "CASTELLANO";
        }

        try {
            Language language = Language.valueOf(lang.toUpperCase());
            bookService.listByLang(language);
        } catch (IllegalArgumentException e){
            System.out.println("El idioma ingresado no es valido");
        }
    }

    //LIBROS POR TITULO
    private void findByTitleDB(){
        System.out.println("¿Qué titulo desea buscar?");
        String title = scanner.nextLine();
        List<Book> book = bookRepository.findByTitleContainingIgnoreCase(title);

        if (book.isEmpty()){
            System.out.println("No se encontraron libros con el titulo: " + title);
        } else {
            System.out.println("\nLIBRO ENCONTRADO:\n");
            book.forEach(System.out::println);
        }
    }

    //AUTORES POR NOMBRE
    private void authorsByName(){
        System.out.println("¿El nombre o apellido de qué autor desea buscar?");
        String authorName = scanner.nextLine();
        List<Author> authors = authorService.listAuthorsByName(authorName);

        if (authors.isEmpty()){
            System.out.println("No se encontraron autores con el nombre: " + authorName);
        } else {
            System.out.println("\nAUTOR ENCONTRADO:\n");
            authors.forEach(System.out::println);
        }
    }

    //TOP 5 LIBROS DESCARGADOS
    private void top5Books(){
        List<Book> books = bookRepository.findAll();
        List<Book> top5Books = bookService.getTopFive(books);

        System.out.println("\nTop 5 libros mas descargados: \n");
        top5Books.forEach(book -> System.out.println(book.getDownloads() + "  " + book.getTitle().toUpperCase()));
    }

    //ESTADISTICAS DE LA BDD
    private void showStatistics(){
        dataStatisticsService.showStatistics();
    }
}

