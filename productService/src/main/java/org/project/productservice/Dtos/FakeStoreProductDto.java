package org.project.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreProductDto implements Serializable {
    private Long id ;
    private String title ;
    private Long price ;
    private String category ;
    private String description ;
    private String image ;
}
