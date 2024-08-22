package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDbService")
public class ProductDBService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductDBService(ProductRepository productRepository,
                            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, Double price,
                                 String imageUrl, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        product.setCategory(getCategoryFromDB(categoryName));

        return productRepository.save(product);
    }

    @Override
    public Product partialUpdate(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product that you want to update is not found");
        }

        Product productToUpdate = productOptional.get();
        if(product.getTitle() != null) {
            productToUpdate.setTitle(product.getTitle());
        }

        if(product.getDescription() != null) {
            productToUpdate.setDescription(product.getDescription());
        }

        // TODO: Add other fields

        if(product.getCategory() != null) {
            productToUpdate.setCategory(
                    getCategoryFromDB(product.getCategory().getName())
            );
        }


        return productRepository.save(productToUpdate);
    }

    private Category getCategoryFromDB(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);

        if(categoryOptional.isEmpty()) {
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);
        }

        return categoryOptional.get();
    }
}
