package com.example.kawther_nalis.carearfor60;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private Button mrecordview,mPlayview;
    private VideoView mvideoview;
    private int ACTIVITY_START_CAMERA_APP=0;
    public static TextView username;
    ImageView exit;
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrecordview=(Button)findViewById(R.id.recordButton);
        mPlayview=(Button)findViewById(R.id.Playbutton);
        mvideoview=(VideoView)findViewById(R.id.videoView);
        username=(TextView)findViewById(R.id.username);
        exit=(ImageView)findViewById(R.id.exit);






            mPlayview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mvideoview.start();
            }
        });

        mrecordview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callVideoappIntent=new Intent();
                callVideoappIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                callVideoappIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);
                startActivityForResult(callVideoappIntent,ACTIVITY_START_CAMERA_APP);
            }
        });


        Intent intent = getIntent();
        String message = intent.getStringExtra(SignIn.EXTRA_MESSAGE);

        if(intent.hasExtra("myname"))
        {
            String newmessage = intent.getStringExtra("myname");
           // Toast.makeText(G.context,newmessage,Toast.LENGTH_SHORT).show();
            username.setText(newmessage);

        }


        if(intent.hasExtra(SignIn.EXTRA_MESSAGE))
        {
            preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("message", message);
            editor.commit();
            username.setText(message);
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("message","");
                editor.commit();
                Intent intent = new Intent(G.context, SignIn.class);
                startActivity(intent);


            }
        });






    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    /*    if (resultCode==RESULT_OK)
        {
            Bundle bundle=data.getExtras();
            String email=bundle.getString("email");
            username.setText(email);
            preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("email",email);
            editor.commit();

        }
*/

         if(requestCode==ACTIVITY_START_CAMERA_APP && resultCode==RESULT_OK)
        {
            Uri videoUri=data.getData();
            mvideoview.setVideoURI(videoUri);
        }
    }
}
