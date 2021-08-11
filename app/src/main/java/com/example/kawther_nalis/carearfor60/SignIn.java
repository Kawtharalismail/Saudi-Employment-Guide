package com.example.kawther_nalis.carearfor60;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kawther_nalis on 10/29/2017.
 */

public class SignIn extends AppCompatActivity {


    public static final String EXTRA_MESSAGE="email";
    TextView signuptxt;
    public static String data="";
    SharedPreferences preferences;
    private static Button loginButton;

    private static CheckBox show_hide_password;
    private static EditText emailid, password;


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);






        if (resultCode==RESULT_OK)
        {
            Bundle bundle=data.getExtras();
            String Do=bundle.getString("Do");

            preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("Do",Do);
            editor.commit();

            Intent intent=new Intent(G.context,MainActivity.class);
            startActivityForResult(intent,0);

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        final String idintity=getIntent().getStringExtra("from");




        show_hide_password = (CheckBox)findViewById(R.id.show_hide_password);
        emailid = (EditText) findViewById(R.id.login_emailid);
        password = (EditText)findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.loginBtn);

        show_hide_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(show_hide_password.isChecked())
                {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }


            }
        });



        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
        String message=preferences.getString("message","User Name");
       // String message=preferences.getString("message","User Name");
       //Toast.makeText(G.context,message,Toast.LENGTH_SHORT).show();


        if (!message.equals("User Name") && !message.equals(""))
        {


            //Toast.makeText(G.context,message,Toast.LENGTH_SHORT).show();

            // MainActivity.username.setText(message);
            Intent intent = new Intent(G.context, MainActivity.class);
            intent.putExtra("myname",message);
            startActivity(intent);


        }





        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailid.getText().toString();
                String pass=password.getText().toString();


                new  AsyncTaskConnect("http://akleel.com/index60.php",email,pass).execute();



                //////prograssFordontShowAllMessage
                final ProgressDialog dialog=new ProgressDialog(SignIn.this);
                dialog.setMessage("Please Wait");
                dialog.show();


                final Timer timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!data.equals(""))
                                {
                                    /*for (int i = 0; i < 20; i++) {
                                    Toast.makeText(SignIn.this,data,Toast.LENGTH_SHORT).show();}*/
                                    dialog.cancel();
                                    if (data.equals("you are not signing in"))
                                    {
                                        Toast.makeText(G.context,"Invalid Inputs",Toast.LENGTH_SHORT).show();
                                        timer.cancel();
                                    }

                                    // Toast.makeText(ActivityUserSign.this,data,Toast.LENGTH_SHORT).show();
                                    else
                                    {

                                        Intent intent = new Intent(G.context, MainActivity.class);
                                        intent.putExtra(EXTRA_MESSAGE, data);
                                       // Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();

                                        ////back were you came
                                        startActivity(intent);
                                        timer.cancel();
                                        finish();
                                    }

                                }
                            }
                        });
                    }
                },1,1000);
            }
        });


        signuptxt=(TextView)findViewById(R.id.signup);

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             Intent intent=new Intent(G.context,SignUp.class);
                intent.putExtra("to",idintity);
                startActivityForResult(intent,0);




            }
        });
    }


}
