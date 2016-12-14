package es.dpinfo.tour;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import es.dpinfo.tour.fragments.EditPoiFragment;
import es.dpinfo.tour.fragments.ListPoiFragment;
import es.dpinfo.tour.models.Poi;

/**
 * Created by dprimenko on 12/12/16.
 */

public class MainActivity extends AppCompatActivity implements ListPoiFragment.ListPoiFragmentListener, EditPoiFragment.EditPoiFragmentListener{

    private EditPoiFragment editFragment;
    private ListPoiFragment listFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = ListPoiFragment.newInstance(null);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, listFragment).commit();
    }

    @Override
    public void onEditPoiFragmentListener(Bundle bundle) {

        editFragment = EditPoiFragment.newInstance(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, editFragment);
        ft.addToBackStack("EDIT");
        ft.commit();
    }

    @Override
    public void onListPoiFragmentListener(Bundle bundle) {
        listFragment = ListPoiFragment.newInstance(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, listFragment);
        ft.commit();
    }
}
