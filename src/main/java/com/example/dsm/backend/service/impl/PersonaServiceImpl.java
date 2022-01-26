package com.example.dsm.backend.service.impl;

import com.example.dsm.backend.commons.GenericServiceImpl;
import com.example.dsm.backend.dto.PersonaDTO;
import com.example.dsm.backend.model.Persona;
import com.example.dsm.backend.service.api.PersonaServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, PersonaDTO> implements PersonaServiceAPI {

    @Autowired
    private Firestore firestore;

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("personas");
    }

}
