package es.dpinfo.tour;

import android.util.Log;

import java.util.ArrayList;

import es.dpinfo.tour.interfaces.IRepository;
import es.dpinfo.tour.models.Poi;

/**
 * Created by dprimenko on 13/12/16.
 */
public class RepositoryImpl implements IRepository {

    private ArrayList<Poi> listPoi;

    private static RepositoryImpl ourInstance = new RepositoryImpl();

    public static RepositoryImpl getInstance() {
        return ourInstance;
    }

    private RepositoryImpl() {
        listPoi = new ArrayList<>();

        listPoi.add(new Poi(0, "Museo Picasso", "museum", "Museo muy bonito", 3));
        listPoi.add(new Poi(1, "Restaurante La Huerta", "restaurant", "Restaurante en la Carihuela", 5));
        listPoi.add(new Poi(2, "Horno Beach", "bar", "Mojitos regalados...", 2));
    }

    @Override
    public ArrayList<Poi> getAllProducts() {
        return listPoi;
    }

    @Override
    public Poi getPoiById(int id) {
        return listPoi.get(id);
    }

    @Override
    public void deletePoi(Poi poi) {
        if (listPoi != null) {
            listPoi.remove(poi);
        }
    }

    @Override
    public void addPoi(Poi poi) {
        if (listPoi != null) {
            listPoi.add(poi);
        }
    }

    @Override
    public void updatePoi(Poi poi) {
        if (listPoi != null) {
            int index = listPoi.indexOf(poi);

            if (index != -1) {
                listPoi.set(index, poi);
            }
        }
    }
}
