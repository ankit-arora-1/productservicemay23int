package com.scaler.productservice.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public Integer sumFromService(int a, int b) {
        System.out.println("some logic");
        System.out.println("some more logic");
        int result = a + b;
        System.out.println("some logic after add");

        return result;
    }
}
