package com.scaler.productservice.calculator;

import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {
    private CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int add(int a, int b) {
        System.out.println("Some logic from PC");
        System.out.println("Some more logic from PC");

        int result = calculatorService.sumFromService(a, b);

        System.out.println("some more logic after calling service");

        return result;
    }
}
