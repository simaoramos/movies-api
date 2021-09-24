package br.com.sitalomr.moviesapi.domain.exception;

import br.com.simaoramos.exceptioncore.RecordAlreadyExistsException;

public class GenreExistingNameException extends RecordAlreadyExistsException {
    public GenreExistingNameException() {
        super("A genre record with the given name already exists");
    }
}
