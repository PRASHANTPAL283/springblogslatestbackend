package com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseException  {

    @ExceptionHandler(GlobalExceptionClass.class)
    public ResponseEntity<ErrorResponse> checkGlobalException(GlobalExceptionClass exceptionClass){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(exceptionClass.getCode());
        errorResponse.setCause(String.valueOf(exceptionClass.getCause()));
        if(exceptionClass.getCode().equalsIgnoreCase("404")){
            errorResponse.setStatus(HttpStatus.NOT_FOUND);

        }
        else if(exceptionClass.getCode().equalsIgnoreCase("500")){
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if(exceptionClass.getCode().equalsIgnoreCase("403")){
            errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        }
        else if( exceptionClass.getCode().equalsIgnoreCase("400")){
            errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        }
        else {
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(errorResponse.getStatus())
                .body(errorResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String >> checkValidations(MethodArgumentNotValidException ex){
        Map<String,String> errorResponse=new HashMap<>();
        ex.getBindingResult().getFieldErrors().stream().forEach(e->{
            errorResponse.put(e.getField(),e.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);


    }
}
