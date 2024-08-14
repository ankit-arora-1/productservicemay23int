package com.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDto {
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}
