package com.scaler.productservice.controlleradvice;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dtos.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong");
        errorDto.setStatus("FAILURE");

        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("FAILURE");
        errorDto.setMessage(exception.getMessage());

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto,
                HttpStatusCode.valueOf(404));

        return  responseEntity;
    }
}
