package com.scaler.productservice.controllers;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.commons.AuthenticationCommons;
import com.scaler.productservice.dtos.*;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductController {
    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("fakeStoreService") ProductService productService,
                             AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }


    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) throws ProductNotFoundException {
        UserDto userDto = authenticationCommons
                .validateToken(token);
        if(userDto == null) {
            throw new RuntimeException("Invalid Token"); // TODO: Create exception for this
        }

        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product: products) {
            responseDtos.add(ProductResponseDto.from(product));
        }

        return responseDtos;
    }

    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategoryName()
        );

        return ProductResponseDto.from(product);
    }


    public void deleteProduct() {

    }


    @PatchMapping("/product/{id}")
    public void partialUpdateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto requestDto) {

    }

    // Only invoked in ProductController class.
    // If there is any other controller that throws NPE, this won't be called
//    @ExceptionHandler(NullPointerException.class)
//    public ErrorDto nullPointerExceptionHandler() {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("Something went wrong");
//        errorDto.setStatus("FAILURE");
//
//        return errorDto;
//    }
}
