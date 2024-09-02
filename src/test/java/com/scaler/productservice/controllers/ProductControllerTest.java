package com.scaler.productservice.controllers;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dtos.ProductResponseDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @MockBean
    @Qualifier("productDbService")
    private ProductService productService;

    @Autowired
    private ProductController productController; // class under test

    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setTitle("abc");
        dummyProduct.setDescription("abcd");
        dummyProduct.setPrice(100.0);
        dummyProduct.setImageUrl("img/url");

        Category category = new Category();
        category.setId(1L);
        category.setName("electronics");
        dummyProduct.setCategory(category);

        when(productService.getProductById(1L))
                .thenReturn(dummyProduct);

        ProductResponseDto responseDto =
                productController.getProductById(1L);

        assertEquals(1L, responseDto.getId());
        assertEquals("abc", responseDto.getTitle());
        assertEquals("abcd", responseDto.getDescription());
    }

    @Test
    public void testGetProductByIdProductIsNull() throws ProductNotFoundException {
        when(productService.getProductById(1L))
                .thenReturn(null);

        ProductResponseDto responseDto =
                productController.getProductById(1L);

        assertNull(responseDto);
    }

}