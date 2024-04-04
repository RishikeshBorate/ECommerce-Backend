package org.project.productservice.services;

import org.project.productservice.Dtos.FakeStoreProductDto;
import org.project.productservice.Dtos.GenericProductDto;
import org.project.productservice.ThirdPartyClients.FakeStoreClient.FakeStoreClientAdapter;
import org.project.productservice.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreServiceImpl")
public class FakeStoreServiceImpl implements ProductService{

    private FakeStoreClientAdapter fakeStoreClientAdapter ;
    private RedisTemplate<String , FakeStoreProductDto> redisTemplate ;


    public FakeStoreServiceImpl(FakeStoreClientAdapter fakeStoreClientAdapter ,
                                RedisTemplate redisTemplate){
        this.fakeStoreClientAdapter = fakeStoreClientAdapter ;
        this.redisTemplate = redisTemplate ;

    }

    private static GenericProductDto convertFakeStoreProductDtotoGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto() ;

        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());

        return genericProductDto ;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get("PRODUCTS",id);

        if(fakeStoreProductDto!=null){
            return convertFakeStoreProductDtotoGenericProductDto(fakeStoreProductDto) ;
        }

        fakeStoreProductDto = fakeStoreClientAdapter.getProductById(id);

        redisTemplate.opsForHash().put("PRODUCTS" , id , fakeStoreProductDto) ;
        
        return convertFakeStoreProductDtotoGenericProductDto(fakeStoreProductDto) ;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<FakeStoreProductDto> fakeStoreProductDtoList = fakeStoreClientAdapter.getAllProducts() ;

        List<GenericProductDto> genericProductDtos = new ArrayList<>() ;

        for(FakeStoreProductDto fakeStoreProductDto1 : fakeStoreProductDtoList ){
            genericProductDtos.add(convertFakeStoreProductDtotoGenericProductDto(fakeStoreProductDto1)) ;
        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {

        FakeStoreProductDto fakeStoreProductDto = fakeStoreClientAdapter.deleteProductById(id);

        return convertFakeStoreProductDtotoGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto , Long id) {

        FakeStoreProductDto fakeStoreProductDto = fakeStoreClientAdapter.updateProductById(genericProductDto , id); ;

        return convertFakeStoreProductDtotoGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto addProduct(GenericProductDto genericProductDto) {

        FakeStoreProductDto fakeStoreProductDto = fakeStoreClientAdapter.addProduct(genericProductDto); ;

        return convertFakeStoreProductDtotoGenericProductDto(fakeStoreProductDto);
    }
}
