package br.com.sitalomr.moviesapi.adapter.rest.exception;

import br.com.sitalomr.moviesapi.domain.exception.GenreExistingNameException;
import br.com.sitalomr.moviesapi.domain.exception.GenreNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final ExceptionResponseFactory exceptionResponseFactory;

    @ExceptionHandler(value = {GenreExistingNameException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, ServletWebRequest request) {
        return this.exceptionResponseFactory.exceptionResponse(BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(value = {GenreNotExistsException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, ServletWebRequest request) {
        return this.exceptionResponseFactory.exceptionResponse(BAD_REQUEST, ex, request);
    }
}
