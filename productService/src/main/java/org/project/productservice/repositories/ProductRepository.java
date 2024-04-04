package org.project.productservice.repositories;

import org.project.productservice.models.Category;
import org.project.productservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

   // List<Product> findByCategory(Category category);
    List<Product> findAllByTitleContainingIgnoreCase(String title , Pageable pageable) ;
}