package com.example.LiterAlura.repository;

import com.example.LiterAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findByNameContainingIgnoreCase(String nombre);

    @Query("SELECT DISTINCT a FROM Author a")
    List<Author> findAllWithoutDuplicates();
}
