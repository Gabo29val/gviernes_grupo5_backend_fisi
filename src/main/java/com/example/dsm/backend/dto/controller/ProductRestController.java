package com.example.dsm.backend.dto.controller;

import com.example.dsm.backend.dto.ProductDTO;
import com.example.dsm.backend.model.Product;
import com.example.dsm.backend.model.Store;
import com.example.dsm.backend.service.api.ProductServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/minimarket/api/v1/products/")
@CrossOrigin("*")
public class ProductRestController {

    @Autowired
    private ProductServiceAPI productServiceAPI;

    @GetMapping("saludo")
    public String saludo() {
        return "Hola products";
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody Product product) throws Exception {
        String id = productServiceAPI.save(product);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@RequestBody Product product, @PathVariable String id) throws Exception {
        String res = productServiceAPI.update(product, id);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public List<ProductDTO> getAll() throws Exception {
        return productServiceAPI.getAll();
    }

    @GetMapping(value = "search/{word}")
    public List<ProductDTO> getProductsByWord(@PathVariable String word) throws Exception {
        return productServiceAPI.getProductsByWord(word);
    }

}
