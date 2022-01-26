package com.example.dsm.backend.service.impl;

import com.example.dsm.backend.commons.GenericServiceImpl;
import com.example.dsm.backend.dto.StoreDTO;
import com.example.dsm.backend.model.Store;
import com.example.dsm.backend.service.api.StoreServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl extends GenericServiceImpl<Store, StoreDTO> implements StoreServiceAPI {

    @Autowired
    private Firestore firestore;

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("stores");
    }
}
