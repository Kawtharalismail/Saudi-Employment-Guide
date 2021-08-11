package com.example.kawther_nalis.carearfor60;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by kawther_nalis on 10/29/2017.
 */

public class Startactivity extends AppCompatActivity {

    ImageView member;
    ImageView company;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        member= (ImageView) findViewById(R.id.member);
        company= (ImageView) findViewById(R.id.company);


        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,SignIn.class);
                intent.putExtra("from","member");
                startActivity(intent);
            }
        });

      /*  company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(G.context,SignIn.class);
                intent.putExtra("from","company");
                startActivity(intent);
            }
        });
        */


    }
}
