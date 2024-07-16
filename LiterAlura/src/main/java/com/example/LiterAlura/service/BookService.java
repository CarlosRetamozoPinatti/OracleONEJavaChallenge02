package com.example.LiterAlura.service;

import com.example.LiterAlura.model.Book;
import com.example.LiterAlura.model.BookData;
import com.example.LiterAlura.model.Language;
import com.example.LiterAlura.model.SearchData;
import com.example.LiterAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    DataConversion conversion;

    //BUSCAR UN LIBRO EN LA API POR TITULO
    public void searchByTitle(String bookTitle, String json){
        try {
            SearchData searchData = conversion.getData(json, SearchData.class);

            Optional<BookData> findBook = searchData.results().stream()
                    .filter(l -> l.title().toUpperCase().contains(bookTitle.toUpperCase()))
                    .findFirst();

            if (findBook.isPresent()){
                BookData bookData = findBook.get();
                Book book = new Book(bookData);
                System.out.println("\n BOOK DATA: \n" + book.toString());
                bookRepository.save(book);
                System.out.println("\n BOOK SAVED SUCCESSFULLY!!");
            } else {
                System.out.println("\n BOOK NOT FOUND!! \n");
            }
        } catch (Exception e){
            System.err.println("AN ERROR HAS OCCURRED!! " + e.getMessage());
        }
    }

    //REMOVER TILDES AL BUSCAR POR IDIOMA
    public String removeTilde(String input){
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    //BUSCAR LIBROS POR IDIOMA
    public List<Book> listByLang(Language language){
        List<Book> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()){
            System.out.println("\n NO DATA WAS FOUND IN DB \n");
        } else {
            System.out.println("\n BOOKS FOUND IN \n " + language.toString());
            books.forEach(System.out::println);
        }
        return books;
    }

    //BUSCAR LOS 5 MAS DESCARGADOS DE LA API
    public List<Book> getTopFive(List<Book> books){
        books.sort(Comparator.comparingInt(Book::getDownloads).reversed());
        return books.subList(0, Math.min(books.size(), 5));
    }
}
