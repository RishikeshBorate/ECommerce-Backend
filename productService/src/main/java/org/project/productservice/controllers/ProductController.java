package org.project.productservice.controllers;

import org.project.productservice.Dtos.ExceptionDto;
import org.project.productservice.Dtos.GenericProductDto;
import org.project.productservice.exceptions.ProductNotFoundException;
import org.project.productservice.services.ProductService;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService ;

    public ProductController(ProductService productService){
        this.productService = productService ;
    }

    @GetMapping("/{id}")
    private GenericProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping
    private List<GenericProductDto> getAllProducts(){

        return productService.getAllProducts() ;
    }

    @DeleteMapping("/{id}")
    private GenericProductDto deleteProductById(@PathVariable("id")Long id ){
        return productService.deleteProductById(id) ;
    }

    @PutMapping("/{id}")
    private GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto , @PathVariable("id")Long id){
        return productService.updateProductById(genericProductDto , id) ;
    }

    @PostMapping
    private GenericProductDto addProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.addProduct(genericProductDto);
    }

    /*
    can handle all the exceptions from controller advice class which is used specifically for exception handling

    @ExceptionHandler(ProductNotFoundException.class)
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDto exceptionDto = new ExceptionDto() ;
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDto.setHttpMessage(productNotFoundException.getMessage());

        return exceptionDto ;
    }
    */
}
