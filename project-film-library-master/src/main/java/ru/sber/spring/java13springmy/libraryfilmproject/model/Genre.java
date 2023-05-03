package ru.sber.spring.java13springmy.libraryfilmproject.model;

public enum Genre {
    FANTASY("Фантастика"),
    SCIENCE_FICTION("Научная фантастика"),
    HORROR("Ужасы"),
    COMEDY("Комедия");
    
    private final String genreTextDisplay;
    
    Genre(String text) {
        this.genreTextDisplay = text;
    }
    
    public String getGenreTextDisplay() {
        return this.genreTextDisplay;
    }
}
