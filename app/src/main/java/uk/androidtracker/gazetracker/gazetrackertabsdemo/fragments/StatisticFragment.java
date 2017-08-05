package uk.androidtracker.gazetracker.gazetrackertabsdemo.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
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

import jp.wasabeef.blurry.Blurry;
import retrofit.RestAdapter;
import uk.androidtracker.gazetracker.gazetrackertabsdemo.R;

import static android.content.ContentValues.TAG;

/**
 * Created by tosch on 11.04.2017.
 */

public class StatisticFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    static MapView mMapView;
    static ImageView blurView;

    private GoogleMap googleMap;
    private LineChart chart;
    static Context context;

    private TextView tw;

    private static Animation map_hide_bottom;
    private static Animation map_show_bottom;
    private static Animation map_hide_top;
    private static Animation map_show_top;
    private static boolean mapShowTop = true;
    private static boolean mapShowBot = true;
    private static boolean animationEnd = true;

    public static Bitmap b;
    public static ViewGroup v;

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

        v = (ViewGroup) getActivity().findViewById(R.id.information);



        //initChart();


        map_hide_bottom = AnimationUtils.loadAnimation(context, R.anim.map_hide_bot);
        map_show_bottom = AnimationUtils.loadAnimation(context, R.anim.map_show_bot);
        map_hide_top = AnimationUtils.loadAnimation(context, R.anim.map_hide_top);
        map_show_top = AnimationUtils.loadAnimation(context, R.anim.map_show_top);

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
                        if(mapShowTop){
                            hide_map();
                        }else{
                            show_map();
                        }
                        return true;
                    }

                });
            }

        });




    }

    private void initChart(){
        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 0));
        entries.add(new Entry(1, 2));
        entries.add(new Entry(2, 4));
        entries.add(new Entry(3, 4));
        entries.add(new Entry(4, 5));
        entries.add(new Entry(5, 8));
        entries.add(new Entry(6, 12));


        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setLineWidth(5);
        dataSet.setCircleRadius(7);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //delete grid lines
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getXAxis().setEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);

        chart.getLegend().setEnabled(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getDescription().setText("");
        animate();


        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
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
        line.width(15f).color(Color.GREEN);
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

    public static void hide_map_top(){
        if(mapShowTop){
            v.startAnimation(map_show_bottom);
            mapShowTop = false;
        }
    }

    public static void show_map_top(){
        if(!mapShowTop){
            v.startAnimation(map_show_bottom);
            mapShowTop = true;
        }
    }

    public void animate(){
        chart.animateY(5000);
    }


}
