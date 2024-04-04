package org.project.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @Column(unique = true)
    private String name ;

  @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "Category")
   private List<Product> productList ;
}
