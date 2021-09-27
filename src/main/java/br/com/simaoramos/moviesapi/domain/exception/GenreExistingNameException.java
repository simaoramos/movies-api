package br.com.simaoramos.moviesapi.domain.exception;

import br.com.simaoramos.core.exception.RecordAlreadyExistsException;

public class GenreExistingNameException extends RecordAlreadyExistsException {
    public GenreExistingNameException() {
        super("A genre record with the given name already exists");
    }
}
