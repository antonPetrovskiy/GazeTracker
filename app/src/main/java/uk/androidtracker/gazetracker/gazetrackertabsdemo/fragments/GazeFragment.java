package uk.androidtracker.gazetracker.gazetrackertabsdemo.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


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
    static TextView gazeString;

    static private int gazes = 0;
    static private int curGazes = 0;
    private ImageButton statistic;
    private ImageView selector1;
    private ImageView selector2;
    private ImageView selector3;
    private ImageView selector4;
    static Context context;


    private boolean isStatisticShowed = false;


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

        gazeString = (TextView)rootView.findViewById(R.id.textView);
        tv2 = (TextView)rootView.findViewById(R.id.textView2);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "lane-narrow.ttf");
        gazeString.setTypeface(face);
        tv2.setTypeface(face);

        selector1 = (ImageView)  rootView.findViewById(R.id.selector1);
        selector2 = (ImageView)  rootView.findViewById(R.id.selector2);
        selector3 = (ImageView)  rootView.findViewById(R.id.selector3);
        selector4 = (ImageView)  rootView.findViewById(R.id.selector4);

        selectorsInit();



        gazeString.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        tv2.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                if(isStatisticShowed){
                    hideStatistic();
                    isStatisticShowed = false;
                }else{
                    showStatistic();
                    isStatisticShowed = true;
                }
                return true;
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gazes++;
                setGazes(gazes);
            }
        });



        return rootView;


    }


    public void selectorsInit(){
        selector1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGazes(5);
                tv2.setText("Daily views");
                hideStatistic();
                isStatisticShowed = false;
            }
        });

        selector2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGazes(23);
                tv2.setText("Weekly views");
                hideStatistic();
                isStatisticShowed = false;
            }
        });

        selector3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGazes(64);
                tv2.setText("Monthly views");
                hideStatistic();
                isStatisticShowed = false;
            }
        });

        selector4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGazes(98);
                tv2.setText("All time views");
                hideStatistic();
                isStatisticShowed = false;
            }
        });
    }

    public void setGazes(int n){
        String s = String.valueOf(n);
        gazes = n;
        gazeString.setText(s);
    }

    public void showStatistic(){
        gazeString.startAnimation(AnimationSingleton.getInstance().getAnimationGazeSmall());
        //tv2.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticHide());

        selector1.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListShow());
        selector2.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListShow());
        selector3.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListShow());
        selector4.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListShow());
    }

    public void hideStatistic(){
        gazeString.startAnimation(AnimationSingleton.getInstance().getAnimationGazeBig());
        //tv2.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticShow());
        gazeString.setVisibility(View.VISIBLE);
        selector1.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListHide());
        selector2.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListHide());
        selector3.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListHide());
        selector4.startAnimation(AnimationSingleton.getInstance().getAnimationStatisticListHide());
    }

}
