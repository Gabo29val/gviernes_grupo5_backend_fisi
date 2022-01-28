package com.example.dsm.backend.service.impl;

import com.example.dsm.backend.commons.GenericServiceImpl;
import com.example.dsm.backend.dto.StoreDTO;
import com.example.dsm.backend.model.Store;
import com.example.dsm.backend.service.api.StoreServiceAPI;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl extends GenericServiceImpl<Store, StoreDTO> implements StoreServiceAPI {

    @Override
    public List<StoreDTO> getCloseStores(Double lat, Double lon, Double radius) throws Exception {
        List<StoreDTO> result = new ArrayList<>();
        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        for (QueryDocumentSnapshot doc : documents) {


            StoreDTO object = doc.toObject(clazz);

            if (isClose(lat, lon, radius, object.getLocation().getLatitude(), object.getLocation().getLongitude())) {
                PropertyUtils.setProperty(object, "id", doc.getId());
                result.add(object);
            }
        }
        return result;
    }

    public boolean isClose(Double lat, Double lon, Double radius, Double latStore, Double lonStore) {
        Double distance = HaverSineDistance(lat, lon, latStore, lonStore);
        //Double distance = calculateDistance(lat, lon, latStore, lonStore);
        System.out.println("Distancia: " + distance);
        if (distance <= radius) {
            System.out.println("lat: " + lat + ", lon: " + lon);
            System.out.println("Distancia: " + distance + " Radius: " + radius);
            return true;
        } else {
            return false;
        }
    }

    public static double HaverSineDistance(double lat1, double lng1, double lat2, double lng2) { // mHager 08-12-2012 // http://en.wikipedia.org/wiki/Haversine_formula // Implementation // convert to radians
        lat1 = Math.toRadians(lat1);
        lng1 = Math.toRadians(lng1);
        lat2 = Math.toRadians(lat2);
        lng2 = Math.toRadians(lng2);
        double dlon = lng2 - lng1;
        double dlat = lat2 - lat1;
        double a = Math.pow((Math.sin(dlat / 2)), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (3958.75 * c);
        dist = Math.round(dist * 100.0) / 100.0;
        return dist;
    }
/*

    private int calculateDistanceByHaversineFormula(Double lon1, Double lat1,
                                                    Double lon2, Double lat2) {
        double earthRadius = 6371; // km

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        Double dlon = lon2 - lon1;
        Double dlat = lat2 - lat1;

        Double sinlat = Math.sin(dlat / 2);
        Double sinlon = Math.sin(dlon / 2);

        Double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
        double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

        double distanceInMeters = earthRadius * c * 1000;

        return (int) distanceInMeters;
    }

    private Double calculateDistance(Double lon1, Double lat1,
                                     Double lon2, Double lat2) {
        final int R = 6371; // Radious of the earth

        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;
        return distance;
    }

    private Double toRad(Double value) {
        return value * Math.PI / 180;
    }
*/


/*    @Override
    public List<O> getAll() throws Exception {
        List<O> result = new ArrayList<O>();
        ApiFuture<QuerySnapshot> query = getCollection().get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();
        for (QueryDocumentSnapshot doc : documents) {
            O object = doc.toObject(clazz);
            PropertyUtils.setProperty(object, "id", doc.getId());
            result.add(object);
        }
        return result;
    }*/

    @Autowired
    private Firestore firestore;

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("stores");
    }
}
