package es.dpinfo.tour.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.dpinfo.tour.R;
import es.dpinfo.tour.RepositoryImpl;
import es.dpinfo.tour.adapters.PoiAdapter;
import es.dpinfo.tour.models.Poi;

/**
 * Created by dprimenko on 12/12/16.
 */

public class ListPoiFragment extends Fragment {

    private ListView lwListPoi;
    private PoiAdapter adapter;

    private ListPoiFragmentListener mCallback;
    private Poi poiReceived;

    public interface ListPoiFragmentListener {
        void onEditPoiFragmentListener(Bundle bundle);
    }

    public ListPoiFragment() {
    }

    public static ListPoiFragment newInstance(Bundle bundle) {
        ListPoiFragment listPoiFragment = new ListPoiFragment();

        listPoiFragment.setArguments(bundle);
        return listPoiFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (ListPoiFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ListPoiFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_list_poi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lwListPoi = (ListView) view.findViewById(R.id.lw_list_poi);
        lwListPoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("poi_key", (Poi)parent.getItemAtPosition(position));
                mCallback.onEditPoiFragmentListener(bundle);
            }
        });

        registerForContextMenu(lwListPoi);

        adapter = new PoiAdapter(getActivity(), R.layout.item_poi, RepositoryImpl.getInstance().getAllProducts());
        try {
            poiReceived = getArguments().getParcelable("poi_key");
            adapter.updatePoi(poiReceived);
        } catch (NullPointerException e) {
            poiReceived = null;
        }
        lwListPoi.setAdapter(adapter);
    }

    private AlertDialog.Builder makeAlertDelete(final AdapterView.AdapterContextMenuInfo info) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());

        alertBuilder.setCancelable(true);
        alertBuilder.setMessage("¿Eliminar?");
        alertBuilder.setNegativeButton("No", null);
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Poi poi = adapter.getItem(info.position);
                adapter.deletePoi(poi);
            }
        });

        return alertBuilder;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_delete_poi:
                makeAlertDelete(info).show();
                break;
            case R.id.action_rate_poi:
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
    }
}
