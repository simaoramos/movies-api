package br.com.sitalomr.moviesapi.domain.exception;

import br.com.simaoramos.exceptioncore.RecordNotFoundException;

public class GenreNotExistsException extends RecordNotFoundException {
    public GenreNotExistsException() {
        super("A genre record with the given id does not exists");
    }
}
