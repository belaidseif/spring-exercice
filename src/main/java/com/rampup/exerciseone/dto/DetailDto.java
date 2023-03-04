package com.rampup.exerciseone.dto;


import com.rampup.exerciseone.model.Detail;

public class DetailDto {
    public DetailDto(Integer quantity, ProductDto product) {
        this.quantity = quantity;
        this.product = product;
    }

    private Integer quantity;

    private ProductDto product;

    public static DetailDto createFromEntity(Detail detail){
        return new DetailDto(detail.getQuantity(), ProductDto.createFromEntity(detail.getProduct()) );
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
