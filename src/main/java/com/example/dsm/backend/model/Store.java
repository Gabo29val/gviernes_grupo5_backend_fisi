package com.example.dsm.backend.model;

import lombok.Data;

@Data
public class Store{
    private String name;
    private String photoUrl;
    private Double rating;
    private String address;
    private String telephone;
    private Location location;

    @Data
    public static class Location {
        private Double latitude;
        private Double longitude;
    }
}
