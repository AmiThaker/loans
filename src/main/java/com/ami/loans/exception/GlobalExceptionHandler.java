package com.ami.loans.exception;

import com.ami.loans.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                        HttpHeaders headers,
                                                                        HttpStatusCode status,
                                                                        WebRequest request){
        Map<String,String> map=new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error->{
                    String fieldName=((FieldError)error).getField();
                    String message=error.getDefaultMessage();

                    map.put(fieldName,message);
                });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex,
                                                                  WebRequest request){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
          request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                            WebRequest request){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(LoansAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleLoansAlreadyExistsException(LoansAlreadyExistsException ex,
                                                                              WebRequest request){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDTO);
    }


}
