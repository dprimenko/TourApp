package es.dpinfo.tour;

import android.content.Context;

/**
 * Created by dprimenko on 14/12/16.
 */
public class SharedPreferences {

    public static void getSharedPreferences(Context context) {

        android.content.SharedPreferences preferences = context.getSharedPreferences("pref_poi", Context.MODE_PRIVATE);



    }
}
