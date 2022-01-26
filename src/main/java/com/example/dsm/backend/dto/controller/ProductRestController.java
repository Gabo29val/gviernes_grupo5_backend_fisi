package com.example.dsm.backend.dto.controller;

import com.example.dsm.backend.dto.ProductDTO;
import com.example.dsm.backend.model.Product;
import com.example.dsm.backend.service.api.ProductServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products/api/v1/")
@CrossOrigin("*")
public class ProductRestController {

    @Autowired
    private ProductServiceAPI productServiceAPI;

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola products";
    }

    @PostMapping(value = "/save/{id}")
    public ResponseEntity<String> save(@RequestBody Product product, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            id = productServiceAPI.save(product);
        } else {
            productServiceAPI.save(product, id);
        }
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public List<ProductDTO> getAll() throws Exception {
        return productServiceAPI.getAll();
    }

}
