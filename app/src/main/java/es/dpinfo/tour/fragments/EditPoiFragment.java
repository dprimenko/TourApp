package es.dpinfo.tour.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import es.dpinfo.tour.R;
import es.dpinfo.tour.models.Poi;

/**
 * Created by dprimenko on 12/12/16.
 */

public class EditPoiFragment extends Fragment {

    private EditPoiFragmentListener mCallback;
    private Poi poiReceived;
    private ArrayAdapter spnAdapter;

    private EditText edtName, edtDescription;
    private Spinner spnCategories;
    private Button btnSend;

    public EditPoiFragment() {
    }

    public interface EditPoiFragmentListener {
        void onListPoiFragmentListener(Bundle bundle);
    }

    public static EditPoiFragment newInstance(Bundle bundle) {
        EditPoiFragment editPoiFragment = new EditPoiFragment();

        editPoiFragment.setArguments(bundle);
        return editPoiFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poiReceived = getArguments().getParcelable("poi_key");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (EditPoiFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement EditPoiFragmentListener.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_poi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        View tmp = view;

        if (tmp != null) {

            edtName = (EditText) tmp.findViewById(R.id.edt_name_edit);
            edtDescription = (EditText) tmp.findViewById(R.id.edt_description_edit);
            btnSend = (Button) tmp.findViewById(R.id.btn_send);
            spnCategories = (Spinner) tmp.findViewById(R.id.spn_category);

            edtName.setText(poiReceived.getmName());
            edtDescription.setText(poiReceived.getmDescription());

            spnAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.categories, R.layout.item_category);
            spnCategories.setAdapter(spnAdapter);

            int indexCategory = 0;
            switch (poiReceived.getmCategory()) {
                case "restaurant":
                    indexCategory = 1;
                    break;
                case "museum":
                    indexCategory = 2;
                    break;
            }

            spnCategories.setSelection(indexCategory);

            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Poi poiToSend = new Poi(
                            poiReceived.getmId(),
                            edtName.getText().toString(),
                            spnCategories.getSelectedItem().toString(),
                            edtDescription.getText().toString(),
                            poiReceived.getmRating());

                    Log.d("category",spnCategories.getSelectedItem().toString());

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("poi_key", poiToSend);

                    mCallback.onListPoiFragmentListener(bundle);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
