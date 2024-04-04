package org.project.productservice.services;

import org.project.productservice.Dtos.GenericProductDto;
import org.project.productservice.Dtos.SearchRequestDto;
import org.project.productservice.Dtos.SortParam;
import org.project.productservice.models.Product;
import org.project.productservice.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository ;

    public SearchService(ProductRepository productRepository){
        this.productRepository = productRepository ;
    }

    public List<GenericProductDto> searchProducts(String query , int pageNumber , int pageSize , List<SortParam> sortParams){
        Sort sort = null ;

        if(sortParams.get(0).getSortType().equalsIgnoreCase("asc")){
            sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
        }else{
            sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
        }

        for(int i=0 ;i<sortParams.size() ;i++){
            if(sortParams.get(i).getSortType().equalsIgnoreCase("asc")){
                sort.and(
                        Sort.by(sortParams.get(0).getSortParamName())
                ).ascending();
            }else{
                sort.and(
                        Sort.by(sortParams.get(0).getSortParamName())
                ).descending();
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber , pageSize , sort) ;

        List<Product> products =
                productRepository.findAllByTitleContainingIgnoreCase(query , pageRequest) ;

        List<GenericProductDto> genericProductDtos = new ArrayList<>() ;

        for(Product product : products){
            genericProductDtos.add(product.from(product)) ;
        }

        return genericProductDtos ;

    }
}
