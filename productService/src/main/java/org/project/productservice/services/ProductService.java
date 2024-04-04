package org.project.productservice.services;

import org.project.productservice.Dtos.GenericProductDto;
import org.project.productservice.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;



public interface ProductService {
    GenericProductDto getProductById(Long id) throws ProductNotFoundException;
    List<GenericProductDto> getAllProducts() ;
    GenericProductDto deleteProductById(Long id) ;
    GenericProductDto updateProductById(GenericProductDto genericProductDto , Long id) ;
    GenericProductDto addProduct(GenericProductDto genericProductDto) ;
}
