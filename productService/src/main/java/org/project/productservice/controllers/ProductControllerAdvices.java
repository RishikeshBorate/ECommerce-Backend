package org.project.productservice.controllers;

import org.project.productservice.Dtos.ExceptionDto;
import org.project.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductControllerAdvices {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDto exceptionDto = new ExceptionDto() ;
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDto.setHttpMessage(productNotFoundException.getMessage());
        return exceptionDto ;

        // we also can return ResponseEntity where we can set httpStatus and other things
    }
}
