package com.rampup.exerciseone.service;

import com.rampup.exerciseone.dto.ProductDto;

import com.rampup.exerciseone.exception.ProductException;
import com.rampup.exerciseone.model.Product;
import com.rampup.exerciseone.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductService service;

    @MockBean
    private ProductRepository repository;

    @Test
    @DisplayName("should return product by id")
    void getProductByIdFound() {
        Product mockProduct = new Product(2L, "Phone", 999.0);
        given(repository.findById(2L)).willReturn(Optional.of(mockProduct));

        ProductDto returnedProduct = service.getProductById(2L);

        Assertions.assertEquals(2L,returnedProduct.getId());
        Assertions.assertEquals("Phone",returnedProduct.getName());
        Assertions.assertEquals(999.0,returnedProduct.getPrice());

    }

    @Test
    @DisplayName("should throw not found exception")
    void getProductByIdNotFound(){
        given(repository.findById(2L)).willReturn(Optional.empty());
        ProductException.ProductNotFound thrown = assertThrows(
                ProductException.ProductNotFound.class,
                () -> service.getProductById(2L)
        );

        Assertions.assertEquals(thrown.getMessage(), "product not found");
    }
}