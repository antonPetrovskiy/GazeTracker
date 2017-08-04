package uk.androidtracker.gazetracker.gazetrackertabsdemo;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by tosch on 02.08.2017.
 */

public class AnimationSingleton {
    private static AnimationSingleton instance = new AnimationSingleton();
    private static Activity rootActivity;

    private AnimationSingleton(){}

    public static AnimationSingleton getInstance(){
        if(instance == null){
            instance = new AnimationSingleton();
        }
        return instance;
    }



    private Animation animationGazeSmall;
    private Animation animationGazeBig;
    private Animation animationStatisticListShow;
    private Animation animationStatisticListHide;

    public void initAnimation(){
        animationGazeSmall = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.gaze_small);
        animationGazeBig = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.gaze_big);
        animationStatisticListShow = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.statistic_list_show);
        animationStatisticListHide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.statistic_list_hide);
    }

    public void setContext(Activity a){
        rootActivity = a;
    }


    public Animation getAnimationGazeSmall() {
        return animationGazeSmall;
    }

    public Animation getAnimationGazeBig() {
        return animationGazeBig;
    }

    public Animation getAnimationStatisticListShow() {
        return animationStatisticListShow;
    }

    public Animation getAnimationStatisticListHide() {
        return animationStatisticListHide;
    }
}
