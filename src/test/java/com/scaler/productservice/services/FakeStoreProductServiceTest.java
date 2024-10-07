package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dtos.FakeStoreProductRequestDto;
import com.scaler.productservice.dtos.FakeStoreProductResponseDto;
import com.scaler.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FakeStoreProductServiceTest {
    private RestTemplate restTemplate =
            Mockito.mock(RestTemplate.class);

    private FakeStoreProductService fakeStoreProductService =
            new FakeStoreProductService(restTemplate, null);

    @Test
    public void testGetProductByIdApiReturnsCorrectResponse() throws ProductNotFoundException {
        FakeStoreProductResponseDto dummyResponse =
                new FakeStoreProductResponseDto();

        dummyResponse.setId(1L);
        dummyResponse.setTitle("abc");
        dummyResponse.setDescription("abcd");
        dummyResponse.setPrice("100.0");
        dummyResponse.setCategory("electronics");
        dummyResponse.setImage("img/url");


        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1",
                FakeStoreProductResponseDto.class
        )).thenReturn(dummyResponse);

        Product product =
                fakeStoreProductService.getProductById(1L);

        assertEquals(1L, product.getId());
        assertEquals("abc", product.getTitle());
    }

    @Test
    public void testGetProductByIdApiResponseIsNull() throws ProductNotFoundException {
        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1",
                FakeStoreProductResponseDto.class
        )).thenReturn(null);

        assertThrows(ProductNotFoundException.class,
                () -> fakeStoreProductService.getProductById(1L));
    }

    @Test
    public void testCreateProduct() {
        FakeStoreProductResponseDto dummyResponse =
                new FakeStoreProductResponseDto();

        dummyResponse.setId(1L);
        dummyResponse.setTitle("abc");
        dummyResponse.setDescription("abcd");
        dummyResponse.setPrice("100.0");
        dummyResponse.setCategory("electronics");
        dummyResponse.setImage("img/url");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductResponseDto.class)
        )).thenReturn(
          dummyResponse
        );

        Product product = fakeStoreProductService
                .createProduct("abc", "abcd",
                        100.0, "img/url",
                        "electronics" );


        assertEquals(1L, product.getId());
        assertEquals("abc", product.getTitle());
    }

    @Test
    public void testCreateProductCheckIfAPICallIsMade() {
        FakeStoreProductResponseDto dummyResponse =
                new FakeStoreProductResponseDto();

        dummyResponse.setId(1L);
        dummyResponse.setTitle("abc");
        dummyResponse.setDescription("abcd");
        dummyResponse.setPrice("100.0");
        dummyResponse.setCategory("electronics");
        dummyResponse.setImage("img/url");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductResponseDto.class)
        )).thenReturn(
                dummyResponse
        );

        Product product = fakeStoreProductService
                .createProduct("abc", "abcd",
                        100.0, "img/url",
                        "electronics" );


        verify(restTemplate, times(1))
                .postForObject(
                        eq("https://fakestoreapi.com/products"),
                        any(),
                        eq(FakeStoreProductResponseDto.class)
                );
    }
}
