package com.example.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record SearchData(

        @JsonAlias("count") Integer count,
        @JsonAlias("results") List<BookData> results
) {
}