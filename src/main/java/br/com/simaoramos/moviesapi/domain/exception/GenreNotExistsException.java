package br.com.simaoramos.moviesapi.domain.exception;

import br.com.simaoramos.core.exception.RecordNotFoundException;

public class GenreNotExistsException extends RecordNotFoundException {
    public GenreNotExistsException() {
        super("A genre record with the given id does not exists");
    }
}
