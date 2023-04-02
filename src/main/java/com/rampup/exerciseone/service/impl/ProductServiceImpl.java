package com.rampup.exerciseone.service.impl;

import com.rampup.exerciseone.dto.ProductDto;
import com.rampup.exerciseone.exception.ProductException;
import com.rampup.exerciseone.model.Product;
import com.rampup.exerciseone.repository.ProductRepository;
import com.rampup.exerciseone.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        Product product = productOpt.orElseThrow(() -> new ProductException.ProductNotFound("product not found"));
        ProductDto productDto = ProductDto.createFromEntity(product);
        return productDto;
    }
}
