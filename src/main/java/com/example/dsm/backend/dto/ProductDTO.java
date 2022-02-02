package com.example.dsm.backend.dto;

import com.example.dsm.backend.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private String id;
    private String name;
    private String nameStore;
    private Double price;
    private String photoUrl;
    private String description;
    private int stock;
    private List<Product.Specification> specifications;

    @Data
    public static class Specification {
        private String name;
        private String value;
    }
}