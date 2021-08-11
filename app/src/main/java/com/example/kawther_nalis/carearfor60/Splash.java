package com.example.kawther_nalis.carearfor60;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by kawther_nalis on 11/5/2017.
 */

public class Splash extends AppCompatActivity{

    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        progressBar=(ProgressBar)findViewById(R.id.progreesbar);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#d8d8d8"), android.graphics.PorterDuff.Mode.SRC_ATOP);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent intent=new Intent(G.context,Startactivity.class);
                        startActivity(intent);
                    }
                },
                3000);
    }



}
