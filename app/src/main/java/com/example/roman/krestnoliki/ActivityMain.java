package com.example.roman.krestnoliki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class ActivityMain extends Activity {

    private CountDownTimer timer;
    TextView present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoMenu();

        present = (TextView) findViewById(R.id.presentID);

        Animation load = AnimationUtils.loadAnimation(this, R.anim.preset);
        present.startAnimation(load);

        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                present.setText("Представляет");
            }
        }.start();

        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                present.setText("Крестики-\n  Нолики");
            }
        }.start();

        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                gotoMenu();
            }
        }.start();
    }

    private void gotoMenu() {
        Intent intent = new Intent(this, ActivityMenu.class);
        startActivity(intent);
    }

}
