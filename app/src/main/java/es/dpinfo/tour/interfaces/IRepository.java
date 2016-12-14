package es.dpinfo.tour.interfaces;

import java.util.ArrayList;

import es.dpinfo.tour.models.Poi;

/**
 * Created by dprimenko on 13/12/16.
 */
public interface IRepository {

    ArrayList<Poi> getAllProducts();
    Poi getPoiById(int id);
    void deletePoi(Poi poi);
    void addPoi(Poi poi);
    void updatePoi(Poi poi);
}
