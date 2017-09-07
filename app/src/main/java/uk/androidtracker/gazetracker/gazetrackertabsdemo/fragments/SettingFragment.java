package uk.androidtracker.gazetracker.gazetrackertabsdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.TabHost;

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
        tabsInit(rootView);

        return rootView;
    }

    public void tabsInit(View rootView){

        TabHost tabHost = (TabHost) rootView.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("",getResources().getDrawable(R.drawable.tab1));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("",getResources().getDrawable(R.drawable.tab2));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("",getResources().getDrawable(R.drawable.tab3));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag4");
        tabSpec.setContent(R.id.tab4);
        tabSpec.setIndicator("",getResources().getDrawable(R.drawable.tab4));
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
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
