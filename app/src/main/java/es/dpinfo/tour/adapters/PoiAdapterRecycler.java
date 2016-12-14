package es.dpinfo.tour.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import es.dpinfo.tour.R;

/**
 * Created by dprimenko on 12/12/16.
 */
public class PoiAdapterRecycler extends RecyclerView.Adapter<PoiAdapterRecycler.PoiViewHolder>{



    @Override
    public PoiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PoiViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PoiViewHolder extends RecyclerView.ViewHolder {

        ImageView imvPoiList;
        TextView txvPoiList;
        RatingBar ratingPoiList;

        public PoiViewHolder(View itemView) {
            super(itemView);

            imvPoiList = (ImageView) itemView.findViewById(R.id.imv_poi_list);
            txvPoiList = (TextView) itemView.findViewById(R.id.txv_name_poi_list);
            ratingPoiList = (RatingBar) itemView.findViewById(R.id.rate_poi_list);
        }
    }
}
