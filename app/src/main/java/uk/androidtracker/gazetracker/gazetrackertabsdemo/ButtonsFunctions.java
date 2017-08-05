package uk.androidtracker.gazetracker.gazetrackertabsdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.provider.CalendarContract;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import jp.wasabeef.blurry.Blurry;
import uk.androidtracker.gazetracker.gazetrackertabsdemo.fragments.StatisticFragment;


/**
 * Created by tosch on 04.07.2017.
 */

public class ButtonsFunctions {

    private ImageButton button_share;
    private ImageButton button_gaze;
    private ImageButton button_statistic;
    private ImageButton button_setting;
    private ImageButton button_vk;
    private ImageButton button_fb;
    private ImageButton button_tw;
    private ImageButton button_main;
    private Animation share_show;
    private Animation share_hide;
    private Animation gaze_show;
    private Animation gaze_hide;
    private Animation statistic_show;
    private Animation statistic_hide;
    private Animation setting_show;
    private Animation setting_hide;



    private Animation fb_show;
    private Animation fb_hide;
    private Animation tw_show;
    private Animation tw_hide;
    private Animation vk_show;
    private Animation vk_hide;

    private Animation connectButtonShow;

    private Intent intent;

    private Activity rootActivity;
    private ViewPager mViewPager;

    public ButtonsFunctions(Activity a, ViewPager p){
        this.rootActivity = a;
        this.mViewPager = p;
        initButtons();
        buttonsAction();
    }

    public void initButtons(){
        button_share = (ImageButton) rootActivity.findViewById(R.id.button_share);
        button_gaze = (ImageButton) rootActivity.findViewById(R.id.button_gaze);
        button_statistic = (ImageButton) rootActivity.findViewById(R.id.button_statistic);
        button_setting = (ImageButton) rootActivity.findViewById(R.id.button_setting);
        button_vk = (ImageButton) rootActivity.findViewById(R.id.fab_vk);
        button_fb = (ImageButton) rootActivity.findViewById(R.id.fab_fb);
        button_tw = (ImageButton) rootActivity.findViewById(R.id.fab_tw);
        button_main = (ImageButton) rootActivity.findViewById(R.id.button_main);
        button_share.setClickable(false);
        button_gaze.setClickable(false);
        button_statistic.setClickable(false);
        button_setting.setClickable(false);






        share_show = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.share_show);
        share_hide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.share_hide);
        gaze_show = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.gaze_show);
        gaze_hide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.gaze_hide);
        setting_show = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.setting_show);
        setting_hide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.setting_hide);
        statistic_show = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.statistic_show);
        statistic_hide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.statistic_hide);

        fb_show = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.fb_show);
        fb_hide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.fb_hide);
        tw_show = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.tw_show);
        tw_hide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.tw_hide);
        vk_show = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.vk_show);
        vk_hide = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.vk_hide);


        connectButtonShow = AnimationUtils.loadAnimation(rootActivity.getApplication(), R.anim.setting_button_show);
    }

    public void buttonsAction(){

        button_main.setOnTouchListener(new View.OnTouchListener()
        {
            long lastDown=0;


            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                float x = event.getRawX();
                float y = event.getRawY();
                //=========================================================
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    lastDown = System.currentTimeMillis();
                    buttonsShow();
                    if(mViewPager.getCurrentItem()==1){
                        StatisticFragment.hide_map();

                    }




                }
                //=========================================================
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(y >= button_gaze.getTop() && y <= button_gaze.getBottom() && x >= button_gaze.getLeft() && x <= button_gaze.getRight()){
                        mViewPager.setCurrentItem(0);
                    }else if(y >= button_setting.getTop() && y <= button_setting.getBottom() && x >= button_setting.getLeft() && x <= button_setting.getRight()){
                        mViewPager.setCurrentItem(2);
                    }else if(y >= button_statistic.getTop() && y <= button_statistic.getBottom() && x >= button_statistic.getLeft() && x <= button_statistic.getRight()){
                        mViewPager.setCurrentItem(1);
                    }
                    buttonsHide();
                    shareButtonsHide();
                    lastDown = 0;


                    if(mViewPager.getCurrentItem()==1){
                        StatisticFragment.show_map();

                    }

                    //=========================================================
                }else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    if (y >= button_main.getTop() && y <= button_main.getBottom() && x >= button_main.getLeft() && x <= button_main.getRight()) {
                        if (System.currentTimeMillis() - lastDown >= 4000 && lastDown!=0 && button_gaze.isClickable()) {
                            buttonsHide();
                        }
                        if (System.currentTimeMillis() - lastDown >= 5000 && lastDown!=0) {
                            lastDown = 0;
                            longPressButton();
                            button_main.setFocusable(false);
                        }
                    }else{
                        lastDown=0;
                    }

                    if(y >= button_share.getTop() && y <= button_share.getBottom() && x >= button_share.getLeft() && x <= button_share.getRight()) {
                        changeColor(1);
                        shareButtonsShow();
                    }else if(y >= button_gaze.getTop() && y <= button_gaze.getBottom() && x >= button_gaze.getLeft() && x <= button_gaze.getRight()){
                        changeColor(2);
                    }else if(y >= button_setting.getTop() && y <= button_setting.getBottom() && x >= button_setting.getLeft() && x <= button_setting.getRight()){
                        changeColor(4);
                    }else if(y >= button_statistic.getTop() && y <= button_statistic.getBottom() && x >= button_statistic.getLeft() && x <= button_statistic.getRight()){
                        changeColor(3);
                    }else{
                        changeColor(0);
                        shareButtonsHide();
                    }
                }
                return false;
            }
        });

    }

    public void changeColor(int n){
        int[] colors = {Color.parseColor("#b929ff"),Color.parseColor("#206aff")};
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);

        switch(n){
            case 0:
                button_gaze.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_statistic.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_share.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_setting.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                break;
            case 1:
                button_gaze.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_statistic.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_share.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#502cb2")));
                button_setting.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                break;
            case 2:
                button_gaze.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#502cb2")));
                button_statistic.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_share.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_setting.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                break;
            case 3:
                button_gaze.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_statistic.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#502cb2")));
                button_share.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_setting.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                break;
            case 4:
                button_gaze.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_statistic.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_share.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46ccf0")));
                button_setting.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#502cb2")));
                break;
        }
    }

    private void longPressButton(){
        buttonsHide();

        intent = new Intent(rootActivity, ConnectActivity.class);
        rootActivity.startActivity(intent);
        rootActivity.overridePendingTransition(R.anim.alpha_true,R.anim.alpha_false);
    }

    private void buttonsShow(){
        if(!button_gaze.isClickable()){
            button_share.startAnimation(share_show);
            button_gaze.startAnimation(gaze_show);
            button_statistic.startAnimation(statistic_show);
            button_setting.startAnimation(setting_show);
            button_share.setClickable(true);
            button_gaze.setClickable(true);
            button_statistic.setClickable(true);
            button_setting.setClickable(true);
        }
    }

    private void buttonsHide(){
        if(button_gaze.isClickable()) {
            button_share.startAnimation(share_hide);
            button_gaze.startAnimation(gaze_hide);
            button_statistic.startAnimation(statistic_hide);
            button_setting.startAnimation(setting_hide);
            button_share.setClickable(false);
            button_gaze.setClickable(false);
            button_statistic.setClickable(false);
            button_setting.setClickable(false);
        }
    }

    private void shareButtonsShow(){
        if(!button_vk.isClickable()) {
            button_vk.startAnimation(vk_show);
            button_fb.startAnimation(fb_show);
            button_tw.startAnimation(tw_show);
            button_vk.setClickable(true);
            button_fb.setClickable(true);
            button_tw.setClickable(true);
        }
    }

    private void shareButtonsHide(){
        if(button_vk.isClickable()) {
            button_vk.startAnimation(vk_hide);
            button_fb.startAnimation(fb_hide);
            button_tw.startAnimation(tw_hide);
            button_vk.setClickable(false);
            button_fb.setClickable(false);
            button_tw.setClickable(false);
        }
    }

}
