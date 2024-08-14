package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product createProduct(String title, String description, Double price,
                              String imageUrl, String categoryName);

    public Product partialUpdate(Long id, Product product);
}
