package com.example.roman.krestnoliki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMenu extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        setContentView(R.layout.activity_menu);

        Button gameButton = (Button) findViewById(R.id.btnGame);
        Button helpButton = (Button) findViewById(R.id.btnHelp);

        gameButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {

            case R.id.btnGame:
                intent = new Intent(this, ActivityGame.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("game_mode", 1);
                startActivity(intent);
                break;

            case R.id.btnHelp:
                intent = new Intent(this, ActivityHelp.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
