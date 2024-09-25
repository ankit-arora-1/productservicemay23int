package com.scaler.productservice;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void getProductByCallingTwoMethods() {
        Optional<Category> category = categoryRepository.findByName("apple");
        List<Product> product = productRepository.findByCategory(category.get());
        System.out.println(product);
    }

    @Test
    public void getProductByCallingOneMethods() {
        List<Product> product = productRepository.findByCategory_NameEquals("apple");
        System.out.println(product);
    }

//    @Test
//    public void test() {
//        List<Product> products = productRepository.getProductsBasedOnCategoryNames("apple");
//        System.out.println("debug");
//    }

    @Test
    @Transactional
    public void getCategory() {
        Optional<Category> category = categoryRepository.findById(1L);
        System.out.println(category.get().getProducts());
    }

//    @Test
//    public void getCategory2() {
//        Optional<Category> category = categoryRepository.findById(1L);
//        System.out.println(category.get().getProducts());
//    }

    @Test
    @Transactional
    public void nplus1Problem() {
        List<Category> categories = categoryRepository.findAll();
        for(Category category: categories) {
            for(Product product: category.getProducts()) {
                System.out.println(product.getTitle());
            }
        }
    }


}
