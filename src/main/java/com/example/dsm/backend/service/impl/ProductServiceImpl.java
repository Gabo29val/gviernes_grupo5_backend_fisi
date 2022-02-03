package com.example.dsm.backend.service.impl;

import com.example.dsm.backend.commons.GenericServiceImpl;
import com.example.dsm.backend.dto.ProductDTO;
import com.example.dsm.backend.model.Product;
import com.example.dsm.backend.service.api.ProductServiceAPI;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, ProductDTO> implements ProductServiceAPI {

    @Autowired
    private Firestore firestore;


    @Override
    public List<ProductDTO> getProductsByWord(String word) throws Exception {
        List<ProductDTO> result = new ArrayList<>();
        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        for (QueryDocumentSnapshot doc : documents) {

            ProductDTO object = doc.toObject(clazz);

            if (match(object, word)) {
                PropertyUtils.setProperty(object, "id", doc.getId());
                result.add(object);
            }
        }

        return result;
    }

    private boolean match(ProductDTO product, String palabra) {
        String p = palabra.trim().toLowerCase();
        String name = product.getName().trim().toLowerCase();
        System.out.println("MATCH: "+name+" "+p);
        if (name.contains(p)){
            return true;
        }
        return false;
    }

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("products");
    }
}
