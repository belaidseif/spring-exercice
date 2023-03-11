package com.rampup.exerciseone.controller;


import com.rampup.exerciseone.dto.ProductDto;
import com.rampup.exerciseone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rampup/api/products")
public class ProductController {

    @Autowired private ProductService productService;


    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable @Valid Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
