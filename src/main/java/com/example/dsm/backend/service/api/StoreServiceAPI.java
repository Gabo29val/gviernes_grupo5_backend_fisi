package com.example.dsm.backend.service.api;

import com.example.dsm.backend.commons.GenericServiceAPI;
import com.example.dsm.backend.dto.StoreDTO;
import com.example.dsm.backend.model.Store;

import java.util.List;

public interface StoreServiceAPI extends GenericServiceAPI<Store, StoreDTO> {
    List<StoreDTO> getCloseStores(Double lat, Double lon, Double radius) throws Exception ;
}
