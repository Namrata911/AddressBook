package com.book.addressbook.aop;

import com.book.addressbook.dto.ErrorResponse;
import com.book.addressbook.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e){
        log.error("Not found exception: {}", e.getMessage());
        ErrorResponse response = new ErrorResponse(e.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //Handler that handles the exception raised because of invalid data that is received as method argument (DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage)//lambda equivalent -> x->x.getDefaultMessage()
                .collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
