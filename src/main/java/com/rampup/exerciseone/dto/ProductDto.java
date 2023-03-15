package com.rampup.exerciseone.dto;



import com.rampup.exerciseone.model.Product;
import org.springframework.hateoas.RepresentationModel;

public class ProductDto extends RepresentationModel<ProductDto> {
    public ProductDto(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private Long id;

    private String name;

    private Double price;

    public static ProductDto createFromEntity(Product product){

        return new ProductDto(product.getId(), product.getName(),product.getPrice());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
