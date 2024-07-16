package com.example.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias ("title") String title,
        @JsonAlias ("authors") List<AuthorData> authors,
        @JsonAlias ("languages") List<Language> languages,
        @JsonAlias ("download_count") Integer downloaded) {
}
