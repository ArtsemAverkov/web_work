package by.it.academy.controllrs.exception;

import by.it.academy.dto.exceptions.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice

public class ControllerExceptionHandler {

    /**
     * this method receives the set value from the
     * session and returns it as a string
     * @param session gets attribute "productRead"
     * @return the received attribute value
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError  handlerEntityNotFountException(EntityNotFoundException e){
        log.info(e.toString());
        return new ResponseError("", e.toString());
    }
}
