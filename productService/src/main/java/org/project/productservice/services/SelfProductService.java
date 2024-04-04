package org.project.productservice.services;

import org.project.productservice.Dtos.GenericProductDto;
import org.project.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public class SelfProductService implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, Long id) {
        return null;
    }

    @Override
    public GenericProductDto addProduct(GenericProductDto genericProductDto) {
        return null;
    }
}
