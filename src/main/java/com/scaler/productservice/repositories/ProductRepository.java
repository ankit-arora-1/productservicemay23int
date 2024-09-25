package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Product> findByCategory(Category category);

    List<Product> findByCategory_NameEquals(String categoryName);

//    @Query("select p from Product p where p.category.name = :categoryName")
//    List<Product> getProductsBasedOnCategoryNames(String categoryName);
//
//    @Query(value = "select * from product p join category c on p.category_id = c.id", nativeQuery = true)
//    List<Product> getProductsBasedOnCategoryNames2();
//
//    @Query(value = CustomQuery.GET_PRODUCTS_FROM_CATEGORY_NAME, nativeQuery = true)
//    List<Product> getProductsBasedOnCategoryNames3();

    Page<Product> findByTitleContaining(String title, Pageable pageable);
}
