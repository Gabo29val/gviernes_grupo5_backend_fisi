package com.example.dsm.backend.dto.controller;

import com.example.dsm.backend.dto.StoreDTO;
import com.example.dsm.backend.model.Store;
import com.example.dsm.backend.service.api.StoreServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/minimarket/api/v1/stores/")
@CrossOrigin("*")
public class StoreRestController {

    @Autowired
    private StoreServiceAPI storeServiceAPI;

    @GetMapping("saludo")
    public String saludo() {
        return "Hola stores";
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody Store store) throws Exception {
        String id = storeServiceAPI.save(store);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@RequestBody Store store, @PathVariable String id) throws Exception {
        String res = storeServiceAPI.update(store, id);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }

    @GetMapping(value = "close/{lat},{lon},{radius}")
    public List<StoreDTO> getTiendasCercanas(@PathVariable Double lat, @PathVariable Double lon, @PathVariable Double radius) throws Exception {
        return storeServiceAPI.getCloseStores(lat, lon, radius);
        //return "Latitud: " + lat + ", Longitud: " + lon + " radius: " + radius;
    }

    @GetMapping(value = "all")
    public List<StoreDTO> getAll() throws Exception {
        return storeServiceAPI.getAll();
    }

}
