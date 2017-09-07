package uk.androidtracker.gazetracker.gazetrackertabsdemo.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import uk.androidtracker.gazetracker.gazetrackertabsdemo.R;

import static android.content.ContentValues.TAG;

/**
 * Created by tosch on 11.04.2017.
 */

public class StatisticFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    static MapView mMapView;
    static ImageView stats;

    private GoogleMap googleMap;
    static Context context;
    private StackView stackView;

    private static Animation map_hide_bottom;
    private static Animation map_show_bottom;
    private static boolean mapShowTop = true;
    private static boolean mapShowBot = true;
    private boolean cardsShow = false;

    public StatisticFragment() {
    }


    public static StatisticFragment newInstance(Context c) {
        StatisticFragment fragment = new StatisticFragment();
        context = c;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistic, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        initMap();

        stackView = (StackView) rootView.findViewById(R.id.stackView);
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.statistic);
        images.add(R.drawable.statistic);
        images.add(R.drawable.statistic);
        images.add(R.drawable.statistic);
        images.add(R.drawable.statistic);
        stackView.setAdapter(new StackAdapter(images));
        stackView.setFadingEdgeLength(0);
        stackView.setVerticalScrollbarPosition(2);

        map_hide_bottom = AnimationUtils.loadAnimation(context, R.anim.map_hide_bot);
        map_show_bottom = AnimationUtils.loadAnimation(context, R.anim.map_show_bot);
        return rootView;
    }

    private void initMap(){
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);
                try {
                    // Customise the styling of the base map using a JSON object defined
                    // in a raw resource file.
                    boolean success = googleMap.setMapStyle(
                            MapStyleOptions.loadRawResourceStyle(context, R.raw.style_json));

                    if (!success) {
                        Log.e(TAG, "Style parsing failed.");
                    }
                } catch (Resources.NotFoundException e) {
                    Log.e(TAG, "Can't find style. Error: ", e);
                }
                route();

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(40.71411847,-74.0062809)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(40.71271972,-74.00967836)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(40.70979201,-74.00997877)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(40.70728709,-74.01203871)));

                googleMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );




                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(!cardsShow){
                            stackView.setVisibility(View.VISIBLE);
                            cardsShow = true;
                        }else{
                            stackView.setVisibility(View.INVISIBLE);
                            cardsShow = false;
                        }
                        return true;
                    }

                });
            }

        });


    }

    public void route(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://maps.googleapis.com")
                .build();


        LatLng start = new LatLng(40.71411847,-74.0062809);
        LatLng start1 = new LatLng(40.71193901,-74.0080905);
        LatLng start2 = new LatLng(40.71271972,-74.00967836);
        LatLng start3 = new LatLng(40.71027997,-74.01122332);
        LatLng start4 = new LatLng(40.70979201,-74.00997877);
        LatLng start5 = new LatLng(40.70728709,-74.01203871);
        LatLng end = new LatLng(40.70774254, -74.01289701);

        PolylineOptions line = new PolylineOptions();
        int[] colors = {Color.parseColor("#b929ff"),Color.parseColor("#206aff")};
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        line.width(15f).color(Color.parseColor("#FF673AB7"));
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

                MarkerOptions startMarkerOptions = new MarkerOptions()
                        .position(start)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.main_button));
                //googleMap.addMarker(startMarkerOptions);

                MarkerOptions endMarkerOptions = new MarkerOptions()
                        .position(end)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.main_button));
                //googleMap.addMarker(endMarkerOptions);



        line.add(start);
        line.add(start1);
        line.add(start2);
        line.add(start3);
        line.add(start4);
        line.add(start5);
        line.add(end);
        latLngBuilder.include(start);
        latLngBuilder.include(start1);
        latLngBuilder.include(start2);
        latLngBuilder.include(start3);
        latLngBuilder.include(start4);
        latLngBuilder.include(start5);
        latLngBuilder.include(end);

        googleMap.addPolyline(line);
        int size = getResources().getDisplayMetrics().widthPixels;
        LatLngBounds latLngBounds = latLngBuilder.build();
        CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, size, size, 25);
        googleMap.moveCamera(track);



    }


    public static void hide_map(){
        if(mapShowBot){
            mMapView.startAnimation(map_hide_bottom);
            mapShowBot = false;
        }
    }

    public static void show_map(){
        if(!mapShowBot){
            mMapView.startAnimation(map_show_bottom);
            mapShowBot = true;
        }
    }

    public static void showStatistic(){

    }

    public static void hideStatistic(){

    }

    private class StackAdapter extends BaseAdapter {
        ArrayList<Integer> images;

        public StackAdapter(ArrayList<Integer> images) {
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View v = inflater.inflate(R.layout.img_stack, viewGroup, false);
            ImageView img = (ImageView) v.findViewById(R.id.imgStack);
            // img.setBackgroundColor(images.get(position));
            img.setImageResource(images.get(position));
            return v;
        }
    }
}
