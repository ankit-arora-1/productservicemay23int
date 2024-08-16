package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dtos.FakeStoreProductRequestDto;
import com.scaler.productservice.dtos.FakeStoreProductResponseDto;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class
        );

        if(responseDto == null) {
            throw new ProductNotFoundException("Product with id: " + id + " not found");
        }

        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDto[] responseDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductResponseDto[].class
        );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductResponseDto responseDto: responseDtos) {
            products.add(responseDto.toProduct());
        }

        return products;
    }

    @Override
    public Product createProduct(String title, String description,
                              Double price, String imageUrl, String categoryName) {

        FakeStoreProductRequestDto requestDto = new FakeStoreProductRequestDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setCategory(categoryName);
        requestDto.setPrice(price);
        requestDto.setImage(imageUrl);

        FakeStoreProductResponseDto responseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDto,
                FakeStoreProductResponseDto.class
        );


        return responseDto.toProduct();
    }

    @Override
    public Product partialUpdate(Long id, Product product) {
        HttpEntity<Product> httpEntity =
                new HttpEntity<>(product); // Add dto object here

        ResponseEntity<FakeStoreProductResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products" + id,
                HttpMethod.PATCH,
                httpEntity, // use dto here
                FakeStoreProductResponseDto.class
        );

        FakeStoreProductResponseDto responseDto = responseEntity.getBody();

        return null;
    }
}
