package by.it.academy.controllers.exception;

import by.it.academy.dtos.exceptions.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError  handlerEntityNotFountException (EntityNotFoundException e){
        log.info(e.toString());
        return new ResponseError("INCORRECT REQUEST", e.toString());// todo rename, message
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    private ResponseError serverErrorRuntime (RuntimeException ex) { //todo rename
        return new ResponseError("INTERNAL SERVER ERROR", ex.toString());
    }
}
