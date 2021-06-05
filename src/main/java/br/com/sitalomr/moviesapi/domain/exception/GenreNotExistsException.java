package br.com.sitalomr.moviesapi.domain.exception;

public class GenreNotExistsException extends Exception {
    public GenreNotExistsException() {
        super("A genre record with the given id does not exists");
    }
}
