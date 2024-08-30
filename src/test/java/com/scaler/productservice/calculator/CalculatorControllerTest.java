package com.scaler.productservice.calculator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculatorControllerTest {

//    private CalculatorService calculatorService =
//            Mockito.mock(CalculatorService.class); // dummy class
//
//    private CalculatorController calculatorController =
//            new CalculatorController(calculatorService);

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private CalculatorController calculatorController;


    @Test
    public void testAddWhenTwoIntegersArePassed() {
        // If you won't provide "when" and "then",
        // actual logic WILL NOT be called.
        // It will return the default value of that data type
        when(calculatorService.sumFromService(5, 10))
                .thenReturn(15);

        int a = 5;
        int b = 10;
        int expectedResult = 15;

        int result = calculatorController.add(a, b);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testAddWhenTwoIntegersArePassed2() {
        when(calculatorService.sumFromService(anyInt(), anyInt()))
                .thenReturn(20);

        int a = 10;
        int b = 10;
        int expectedResult = 20;

        int result = calculatorController.add(a, b);

        Assertions.assertEquals(expectedResult, result);
    }


}
