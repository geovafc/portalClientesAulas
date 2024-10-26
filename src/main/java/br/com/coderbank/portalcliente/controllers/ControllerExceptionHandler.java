package br.com.coderbank.portalcliente.controllers;


import br.com.coderbank.portalcliente.dtos.responses.ErrorResponseDTO;
import br.com.coderbank.portalcliente.exceptions.ClienteJaExistenteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({ClienteJaExistenteException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail conflict(final Throwable exception) {
        final var exceptionMessage = exception.getMessage();

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exceptionMessage);
        problemDetail.setTitle("conflict");

        problemDetail.setType(URI.create("https://www.coderbank.com.br/fordevs/docs/erros/conflict"));

        log.error("m=conflict, ex= {}", exceptionMessage);
        return problemDetail;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleValidation(final MethodArgumentNotValidException ex) {

        final var errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach( (error) -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();

                    errors.put(fieldName, errorMessage);
                        }
                );

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errors.toString());
        problemDetail.setTitle("Data sent in the request is invalid");
        problemDetail.setType(URI.create("https://www.coderbank.com.br/fordevs/docs/erros/invalids-requests"));

        log.error("m=conflict, ex= {}", errors);
        return problemDetail;
    }



}
