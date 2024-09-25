package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(String query, int pageNumber, int pageSize) {
        Sort sort = Sort.by("title").descending()
                .and(Sort.by("price")).ascending();

//        List<String> sortValues = new ArrayList<>();
//        for(String sortValue: sortValues) {
//            sort = Sort.by(sortValue).ascending();
//        }


        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return productRepository.findByTitleContaining(query, pageable);
    }
}
