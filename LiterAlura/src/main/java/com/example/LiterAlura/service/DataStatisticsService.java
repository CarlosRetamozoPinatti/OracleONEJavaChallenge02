package com.example.LiterAlura.service;

import com.example.LiterAlura.model.Author;
import com.example.LiterAlura.model.Book;
import com.example.LiterAlura.model.Language;
import com.example.LiterAlura.repository.AuthorRepository;
import com.example.LiterAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataStatisticsService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void showStatistics(){
        System.out.println("Database contains the following information: ");
    }

    private void showTotalBookRecount(){
        long totalRecount = bookRepository.count();
        System.out.println(" Books loaded on db: " + totalRecount);
    }

    private void showTotalAuthorRecount(){
        List<Author> authors = authorRepository.findAllWithoutDuplicates();
        long totalRecount = authors.size();
        System.out.println(" Authors loaded on db: " + totalRecount);
    }

    private void showDownloadStatistics(){
        System.out.println(" Statistic Download Data: ");
        DoubleSummaryStatistics downloadStatistics = bookRepository.findAll().stream()
                .mapToDouble(Book::getDownloads)
                .filter(downloads -> downloads > 0)
                .summaryStatistics();
        System.out.println(" - Max. Downloaded: " + downloadStatistics.getMax());
        System.out.println(" - Min. Downloaded: " + downloadStatistics.getMin());
        System.out.println(" - Avg. Downloaded: " + downloadStatistics.getAverage());
    }

    private void showRecountByLang(){
        System.out.println(" Book recount by Language: ");
        Map<Language, Long> recountByLang = bookRepository.findAll().stream()
                                            .collect(Collectors.groupingBy(Book::getLanguage, Collectors.counting()));

        recountByLang.forEach((language, recount) ->
                System.out.println("   - " + language.toString().toLowerCase() + ": " + recount));
    }

}
