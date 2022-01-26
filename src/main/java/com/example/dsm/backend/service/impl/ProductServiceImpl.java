package com.example.dsm.backend.service.impl;

import com.example.dsm.backend.commons.GenericServiceImpl;
import com.example.dsm.backend.dto.ProductDTO;
import com.example.dsm.backend.model.Product;
import com.example.dsm.backend.service.api.ProductServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, ProductDTO> implements ProductServiceAPI {

    @Autowired
    private Firestore firestore;

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("products");
    }
}
