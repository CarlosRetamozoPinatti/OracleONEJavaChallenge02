package com.example.LiterAlura.repository;

import com.example.LiterAlura.model.Book;
import com.example.LiterAlura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByLanguage(Language language);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
