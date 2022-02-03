package com.example.dsm.backend.dto;

import com.example.dsm.backend.model.Store;
import lombok.Data;

import java.io.Serializable;

@Data
public class StoreDTO implements Serializable {
    private String id;
    private String name;
    private String photoUrl;
    private Double rating;
    private String address;
    private String telephone;
    private Store.Location location;

    @Data
    public static class Location implements Serializable {
        private Double latitude;
        private Double longitude;
    }
}
