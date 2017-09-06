package com.example.roman.krestnoliki;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityHelp extends Activity implements View.OnClickListener {

    Button btnMenuH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        setContentView(R.layout.activity_help);

        btnMenuH = (Button) findViewById(R.id.buttonBack);
        btnMenuH.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonBack) {
            Intent intent = new Intent(this, ActivityMenu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}

