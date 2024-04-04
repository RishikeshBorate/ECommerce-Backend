package org.project.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.project.productservice.Dtos.GenericProductDto;


@Getter
@Setter
@Entity
//@Document(indexName = "products")
public class Product extends BaseModel{
    private String title ;
    private String description ;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Category Category ;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false)
    @JoinColumn(unique = true)
    private Price Price ;

    public GenericProductDto from(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto() ;
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setPrice(product.getPrice().getValue());

        return genericProductDto ;
    }

}
