package com.example.roman.krestnoliki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ActivityGame extends Activity implements View.OnClickListener {

    ImageButton[] arrCell;
    Bitmap imageX, imageO;
    int[] array;
    Button startButton, menuButton;
    boolean stopGame;
    int gameMode;
    int turn;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        setContentView(R.layout.activity_game);

        arrCell = new ImageButton [9];

        arrCell[0] = (ImageButton) findViewById(R.id.cellID0);
        arrCell[1] = (ImageButton) findViewById(R.id.cellID1);
        arrCell[2] = (ImageButton) findViewById(R.id.cellID2);
        arrCell[3] = (ImageButton) findViewById(R.id.cellID3);
        arrCell[4] = (ImageButton) findViewById(R.id.cellID4);
        arrCell[5] = (ImageButton) findViewById(R.id.cellID5);
        arrCell[6] = (ImageButton) findViewById(R.id.cellID6);
        arrCell[7] = (ImageButton) findViewById(R.id.cellID7);
        arrCell[8] = (ImageButton) findViewById(R.id.cellID8);


        for (int i = 0; i<9; i++) {
            arrCell[i].setOnClickListener(this);
        }

        imageX = BitmapFactory.decodeResource(getResources(), R.drawable.x);
        imageO = BitmapFactory.decodeResource(getResources(), R.drawable.o);

        array = new int [9];

        for (int i = 0; i < 9; i++) {
            array[i] = 0;
        }

        menuButton = (Button) findViewById(R.id.buttonMenu);
        startButton = (Button) findViewById(R.id.buttonStart);
        menuButton.setOnClickListener(this);
        startButton.setOnClickListener(this);

        stopGame = false;

        gameMode = getIntent().getIntExtra("game_mode", 1);
        turn = 1;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putIntArray("array", array);
        outState.putInt("turn",turn);
        TextView tvStat = (TextView) findViewById(R.id.tvStatus);
        outState.putString("status", tvStat.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        array = savedInstanceState.getIntArray("array");
        turn = savedInstanceState.getInt("turn");
        String status = savedInstanceState.getString("status");
        TextView tvStat = (TextView) findViewById(R.id.tvStatus);
        tvStat.setText(status);
        draw();
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.cellID0:
                moving(0);
                break;
            case R.id.cellID1:
                moving(1);
                break;
            case R.id.cellID2:
                moving(2);
                break;
            case R.id.cellID3:
                moving(3);
                break;
            case R.id.cellID4:
                moving(4);
                break;
            case R.id.cellID5:
                moving(5);
                break;
            case R.id.cellID6:
                moving(6);
                break;
            case R.id.cellID7:
                moving(7);
                break;
            case R.id.cellID8:
                moving(8);
                break;
            case R.id.buttonMenu:
                intent = new Intent(this, ActivityMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.buttonStart:
                intent = new Intent(this, ActivityGame.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("game_mode", gameMode);
                startActivity(intent);
                break;
        }
    }

    public void draw(){
        for(int i = 0; i < 9; i++){
            if (array[i]==1){
                arrCell[i].setImageBitmap(imageX);
            }
            if (array[i]==2){
                arrCell[i].setImageBitmap(imageO);
            }
        }
    }
    private void moving(int n) {
        if(stopGame) return;
        int oturn = 0;
        int xturn = 1;

        if(array[n] == 0) {
            if(gameMode == 1) {
                if(turn == 1) {
                    arrCell[n].setImageBitmap(imageX);
                    status(oturn);
                    array[n] = 1;
                    turn = 2;
                } else {
                    arrCell[n].setImageBitmap(imageO);
                    status(xturn);
                    array[n] = 2;
                    turn = 1;
                }
            }
        } else {
            return;
        }

        checkMove();
        if(stopGame) return;

    }

    private void checkMove() {
        int emptyCell = 0;

        for (int i = 0; i < 9; i++) {
            if (array[i] == 0) {
                emptyCell++;
            }
        }
        int owin = 3, xwin = 2, draw = 4;
        if (check(1)) {
            status(xwin);
            stopGame = true;
        } else if (check(2)) {
            status(owin);
            stopGame = true;
        } else if (emptyCell == 0) {
            status(draw);
            stopGame = true;
        }
    }

    private boolean check(int n) {
        int checkCell = 0, goriz, vertik, diag = 0;

        for (int i = 0; i < 3; i++) {
            goriz = 0;
            for (int j = 0; j < 3; j++) {
                if(array[checkCell] == n) goriz++;
                checkCell++;
            }
            if(goriz == 3) {
                return true;
            }
        }

        checkCell = 0;
        for (int i = 0; i <= 3; i++) {
            vertik = 0;
            for (int j = 0; j < 3; j++) {
                if(array[checkCell] == n) vertik++;
                checkCell += 3;
            }
            checkCell = i;
            if(vertik == 3) {
                return true;
            }
        }

        checkCell = 0;
        for (int j = 0; j < 3; j++) {
            if(array[checkCell] == n) diag++;
            checkCell += 4;
        }
        if(diag == 3) {
            return true;
        }

        diag = 0;
        checkCell = 0;
        for (int j = 0; j < 3; j++) {
            checkCell += 2;
            if(array[checkCell] == n) diag++;
        }
        if(diag == 3) {
            return true;
        }

        return false;
    }

    private void status(int i) {
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        if (i==0){
            tvStatus.setText(R.string.oTurn);
        }else if (i == 1) {
            tvStatus.setText(R.string.xTurn);
        }else if (i == 2){
            tvStatus.setText(R.string.xwin);
        }else if (i == 3){
            tvStatus.setText(R.string.owin);
        }else if (i == 4){
            tvStatus.setText(R.string.draw);
        }
    }

}