package uk.androidtracker.gazetracker.gazetrackertabsdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import uk.androidtracker.gazetracker.gazetrackertabsdemo.R;

/**
 * Created by tosch on 11.04.2017.
 */

public class SettingFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";

    MapView mMapView;
    private GoogleMap googleMap;
    private LineChart chart;

    public SettingFragment() {
    }




    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);




        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
