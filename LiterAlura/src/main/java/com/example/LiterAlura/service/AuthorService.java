package com.example.LiterAlura.service;

import com.example.LiterAlura.model.Author;
import com.example.LiterAlura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<String> listAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .sorted((p1, p2) -> p1.getFullName().compareToIgnoreCase(p2.getFullName()))
                .map(Author::toString)
                .collect(Collectors.toList());
    }

    public List<Author> getAliveAuthors(int year){
        return authorRepository.findAll().stream()
                .filter(author -> author.getDateOfBirth() != null && author.getDateOfBirth() <= year)
                .filter(author -> author.getDateOfDeath() == null || author.getDateOfDeath() >= year)
                .collect(Collectors.toList());
    }

    public void listAliveAuthors(int year){
        List<Author> aliveAuthors = getAliveAuthors(year);
        if (aliveAuthors.isEmpty()){
            System.out.println("No hay registros en la BDD");
        } else {
            System.out.println("Authors Alive By " + year);
            aliveAuthors.forEach(System.out::println);
        }
    }

    public List<Author> listAuthorsByName(String name){
        return authorRepository.findByNameContainingIgnoreCase(name);
    }
}
