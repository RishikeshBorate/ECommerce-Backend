package org.project.productservice.services;

import org.project.productservice.Dtos.GenericProductDto;
import org.project.productservice.Dtos.UserDto;
import org.project.productservice.exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Primary
@Service
public class SelfProductService implements ProductService{

    private RestTemplate restTemplate ;

    public SelfProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate ;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto() ;

        restTemplate.getForEntity("http://authService/users/1" , UserDto.class) ;

        return genericProductDto;
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
