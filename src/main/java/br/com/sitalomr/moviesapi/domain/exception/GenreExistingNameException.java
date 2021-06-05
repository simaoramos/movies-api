package br.com.sitalomr.moviesapi.domain.exception;

public class GenreExistingNameException extends Exception {
    public GenreExistingNameException() {
        super("A genre record with the given name already exists");
    }
}
