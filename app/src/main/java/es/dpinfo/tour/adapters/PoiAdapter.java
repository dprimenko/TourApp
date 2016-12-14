package es.dpinfo.tour.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import es.dpinfo.tour.R;
import es.dpinfo.tour.RepositoryImpl;
import es.dpinfo.tour.models.Poi;

/**
 * Created by dprimenko on 12/12/16.
 */
public class PoiAdapter extends ArrayAdapter<Poi> {

    private Context context;

    public PoiAdapter(Context context, int resource, List<Poi> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        PoiHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_poi, null);

            holder = new PoiHolder();

            holder.imvPoiList = (ImageView) view.findViewById(R.id.imv_poi_list);
            holder.ratingPoiList = (RatingBar) view.findViewById(R.id.rate_poi_list);
            holder.txvPoiList = (TextView) view.findViewById(R.id.txv_name_poi_list);

            view.setTag(holder);

        } else {
            holder = (PoiHolder) view.getTag();
        }

        Poi item = getItem(position);

        switch (item.getmCategory()) {
            case "bar":
                holder.imvPoiList.setImageResource(R.drawable.glass);
                break;
            case "restaurant":
                holder.imvPoiList.setImageResource(R.drawable.fork);
                break;
            case "museum":
                holder.imvPoiList.setImageResource(R.drawable.bank);
                break;
        }

        holder.txvPoiList.setText(item.getmName());
        holder.ratingPoiList.setRating(item.getmRating());

        return view;
    }

    public void updatePoi(Poi poi) {
        RepositoryImpl.getInstance().updatePoi(poi);
        notifyDataSetChanged();
    }

    public void deletePoi(Poi poi) {
        RepositoryImpl.getInstance().deletePoi(poi);
        notifyDataSetChanged();
    }


    class PoiHolder {
        ImageView imvPoiList;
        TextView txvPoiList;
        RatingBar ratingPoiList;
    }
}
