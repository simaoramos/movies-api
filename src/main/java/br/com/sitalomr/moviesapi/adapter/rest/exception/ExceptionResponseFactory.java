package br.com.sitalomr.moviesapi.adapter.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;

@Component
public class ExceptionResponseFactory {
    public ResponseEntity<ExceptionResponse> exceptionResponse(HttpStatus httpStatus, Exception ex, ServletWebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(httpStatus.value())
                .message(ex.getMessage())
                .path(request.getRequest().getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(exceptionResponse);
    }
}
