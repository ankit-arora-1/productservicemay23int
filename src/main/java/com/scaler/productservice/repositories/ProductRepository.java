package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Used for both saving and updating
    // If ID is present, it will update
    // If ID is not present, it will insert
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

}
