package org.project.productservice.ThirdPartyClients.FakeStoreClient;

import org.project.productservice.Dtos.FakeStoreProductDto;
import org.project.productservice.Dtos.GenericProductDto;
import org.project.productservice.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreClientAdapter {
    /*
       In FakeStoreService we called the Third party API's directly from main service .
       To avoid the problem of tightly coupling we implement FakeStoreClientAdapter where it calls the
       FakeStore API's and return objects to FakeStoreService
     */

    private RestTemplateBuilder restTemplateBuilder ;
    //private String getSingleProductUrl = "https://fakestoreapi.com/products/{id}" ;
    private String getSingleProductUrl ;
    private String getAllProductsUrl   ;
    //= "https://fakestoreapi.com/products" ;

    public FakeStoreClientAdapter(RestTemplateBuilder restTemplateBuilder ,
                                  @Value("${FakeStoreAPI.getGenericProduct}") String getSingleProductUrl ,
                                  @Value("${FakeStoreAPI.getAllProducts}")String getAllProductUrl){
        this.restTemplateBuilder = restTemplateBuilder ;
        this.getSingleProductUrl = getSingleProductUrl ;
        this.getAllProductsUrl = getAllProductUrl ;
    }

    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(getSingleProductUrl, FakeStoreProductDto.class , id) ;

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody() ;

        if(fakeStoreProductDto==null){
            throw new ProductNotFoundException("Produt with id : " + id + " not present") ;
        }

        return fakeStoreProductDto ;
    }

    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductDto[].class ) ;

        List<FakeStoreProductDto> fakeStoreProductDtoList = List.of(responseEntity.getBody()) ;


        return fakeStoreProductDtoList;
    }

    public FakeStoreProductDto deleteProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(getSingleProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id) ;

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody() ;

        return fakeStoreProductDto ;
    }


    public FakeStoreProductDto updateProductById(GenericProductDto genericProductDto , Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.execute(getSingleProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody() ;

        return fakeStoreProductDto ;
    }

    public FakeStoreProductDto addProduct(GenericProductDto genericProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(getAllProductsUrl , genericProductDto , FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody() ;


        return fakeStoreProductDto ;
    }
}
