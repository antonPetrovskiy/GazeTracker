package uk.androidtracker.gazetracker.gazetrackertabsdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.fenjuly.library.ArrowDownloadButton;

import java.io.IOException;

public class ConnectActivity extends AppCompatActivity {

    private Animation connectButtonShow;
    private Animation connectButtonPress;

    ArrowDownloadButton connect_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        connect_button = (ArrowDownloadButton) findViewById(R.id.connect_button);
        connectButtonShow = AnimationUtils.loadAnimation(getApplication(), R.anim.setting_button_show);
        connectButtonPress = AnimationUtils.loadAnimation(getApplication(), R.anim.setting_button_press);

        connect_button.startAnimation(connectButtonShow);

        connect_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    connect_button.startAnimating();
                    connect_button.setProgress(connect_button.getProgress() + 2f);
                }
                return false;
            }
        });
    }



    public void backButton(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
