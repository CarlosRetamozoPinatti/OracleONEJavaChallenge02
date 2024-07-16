package com.example.LiterAlura.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String FullName;
    private Integer DateOfBirth;
    private Integer DateOfDeath;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> Books;

    public Author() {
    }

    public Author(AuthorData authorData) {
        this.FullName = authorData.name();
        this.DateOfBirth = authorData.dateOfBirth();
        this.DateOfDeath = authorData.dateOfDeath();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public Integer getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Integer dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public Integer getDateOfDeath() {
        return DateOfDeath;
    }

    public void setDateOfDeath(Integer dateOfDeath) {
        DateOfDeath = dateOfDeath;
    }

    public List<Book> getBooks() {
        return Books;
    }

    public void setBooks(List<Book> books) {
        Books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "Id=" + Id +
                ", FullName='" + FullName + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", DateOfDeath=" + DateOfDeath +
                ", Books=" + Books +
                '}';
    }
}
