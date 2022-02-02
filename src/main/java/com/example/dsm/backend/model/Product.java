package com.example.dsm.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private String name;
    private String nameStore;
    private Double price;
    private String photoUrl;
    private String description;
    private int stock;
    private List<Specification> specifications;

    @Data
    public static class Specification {
        private String name;
        private String value;
    }
}
