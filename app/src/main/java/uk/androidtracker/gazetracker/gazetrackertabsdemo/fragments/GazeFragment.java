package uk.androidtracker.gazetracker.gazetrackertabsdemo.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import jp.wasabeef.blurry.Blurry;
import uk.androidtracker.gazetracker.gazetrackertabsdemo.AnimationSingleton;
import uk.androidtracker.gazetracker.gazetrackertabsdemo.R;

/**
 * Created by tosch on 11.04.2017.
 */


public class GazeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    static TextView tv2;
    static TextView tv;
    ListView list;
    static int gazes = 0;
    private ImageButton statistic;
    static Context context;
    private boolean isStatisticShowed = false;
    String[] enteries = {
            "Total gazes                                     265",
            "Daily gazes                                     12",
            "Weekly gazes                                 54",
            "Monthly gazes                               128"};

    public GazeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static GazeFragment newInstance(Context c) {
        GazeFragment fragment = new GazeFragment();
        context = c;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_gaze, container, false);

        tv = (TextView)rootView.findViewById(R.id.textView);
        tv2 = (TextView)rootView.findViewById(R.id.textView2);
        statistic = (ImageButton) rootView.findViewById(R.id.statistic);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "lane-narrow.ttf");
        tv.setTypeface(face);
        tv2.setTypeface(face);


        // находим список
        list = (ListView) rootView.findViewById(R.id.statistic_view);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, enteries);

        // присваиваем адаптер списку
        list.setAdapter(adapter);


        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gazes++;
                String s = String.valueOf(gazes);
                tv.setText(s);
            }
        });

        statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStatisticShowed){
                    hideStatistic();
                    isStatisticShowed = false;
                }else{
                    showStatistic();
                    isStatisticShowed = true;
                }
            }
        });


        return rootView;


    }

    public void showStatistic(){
        tv.startAnimation(AnimationSingleton.getInstance().getAnimationGazeSmall());
        list.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListShow());
        tv2.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListHide());
    }

    public void hideStatistic(){
        tv.startAnimation(AnimationSingleton.getInstance().getAnimationGazeBig());
        list.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListHide());
        tv2.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListShow());
    }

}
