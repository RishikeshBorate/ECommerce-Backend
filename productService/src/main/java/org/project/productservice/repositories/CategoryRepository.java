package org.project.productservice.repositories;

import org.project.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Override
    Optional<Category> findById(UUID uuid);


    // Category getById(UUID uuid);
}