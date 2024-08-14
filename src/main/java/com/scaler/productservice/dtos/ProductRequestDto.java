package com.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
}
