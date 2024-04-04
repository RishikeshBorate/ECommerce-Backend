package org.project.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    /* GenericProductDto acts as one more abstraction layer between external api object
       and object that are passed to internally External API may change how it return the object
       but having one layer between avoids the unnecessary changed in dto object
     */

    private Long id ;
    private String title ;
    private Long price ;
    private String category ;
    private String description ;
    private String image ;
}
