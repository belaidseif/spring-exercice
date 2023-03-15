package com.rampup.exerciseone.controller;


import com.rampup.exerciseone.dto.ProductDto;
import com.rampup.exerciseone.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/rampup/api/products")
@Tag(name = "product api")
public class ProductController {

    @Autowired private ProductService productService;


    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable @Valid Long productId){
        ProductDto productDto = productService.getProductById(productId);
        Link link = linkTo(methodOn(ProductController.class).getProductById(productId) ).withSelfRel();
        productDto.add(link);
        return ResponseEntity.ok(productDto);
    }
}
