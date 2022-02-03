package com.example.dsm.backend.service.api;

import com.example.dsm.backend.commons.GenericServiceAPI;
import com.example.dsm.backend.dto.ProductDTO;
import com.example.dsm.backend.model.Product;

import java.util.List;

public interface ProductServiceAPI extends GenericServiceAPI<Product, ProductDTO> {
    List<ProductDTO> getProductsByWord(String palabra) throws Exception;
}
