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
@RequestMapping(value = "/stores/api/v1/")
@CrossOrigin("*")
public class StoreRestController {

    @Autowired
    private StoreServiceAPI storeServiceAPI;

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola stores";
    }

    @PostMapping(value = "/save/{id}")
    public ResponseEntity<String> save(@RequestBody Store store, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            id = storeServiceAPI.save(store);
        } else {
            storeServiceAPI.save(store, id);
        }
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public List<StoreDTO> getAll() throws Exception {
        return storeServiceAPI.getAll();
    }

}
