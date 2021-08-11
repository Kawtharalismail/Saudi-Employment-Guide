package com.example.kawther_nalis.carearfor60;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.data;

/**
 * Created by kawther_nalis on 10/29/2017.
 */


public class SignUp extends AppCompatActivity {



    TextView alreadyUser;
    LinearLayout comname;
    LinearLayout comphone;


    EditText editname;
    EditText editEmail;
    EditText editpass;
    EditText editrepass;
    Button signupButton;
    public static String data="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membersignup);

        alreadyUser=(TextView)findViewById(R.id.isalreadyauser);
        comname=(LinearLayout)findViewById(R.id.companynameLayout);
        comphone=(LinearLayout)findViewById(R.id.companyPhoneLayout);
        editname=(EditText)findViewById(R.id.fullName);
        editEmail=(EditText)findViewById(R.id.userEmailId);
        editpass=(EditText)findViewById(R.id.password);
        editrepass=(EditText)findViewById(R.id.confirmPassword);
        signupButton=(Button)findViewById(R.id.signUpBtn);




        if(getIntent().getStringExtra("to").equals("member"))
        {
            comphone.setVisibility(View.GONE);
            comname.setVisibility(View.GONE);
        }else if(getIntent().getStringExtra("to").equals("company"))
        {   // do here
        }
        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(G.context,SignIn.class);
                startActivity(intent);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=editname.getText().toString();
                String email=editEmail.getText().toString();
                String pass=editpass.getText().toString();
                String repass=editrepass.getText().toString();

                if ((pass.equals(repass)))
                {
                    new AsyncTaskInsertUser("http://akleel.com/insertUser60.php",name,email,pass).execute();


                    final ProgressDialog dialog=new ProgressDialog(SignUp.this);
                    dialog.setMessage("Please Wait");
                    dialog.show();

                    //Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();

                        final Timer timer=new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!data.equals(""))
                                    {
                                        dialog.cancel();
                                        // Toast.makeText(G.context,data,Toast.LENGTH_SHORT).show();
                                        if (data.equals("not Ok"))
                                        { Toast.makeText(G.context,"Not Connecting to Server",Toast.LENGTH_SHORT).show();
                                            timer.cancel();}
                                        else if(data.equals("exit"))

                                        {
                                            Toast.makeText(G.context,"We have User with this name",Toast.LENGTH_SHORT).show();
                                            timer.cancel();
                                        }

                                        else
                                        {

                                            Intent intent = new Intent(G.context,SignIn.class);
                                            intent.putExtra("Do", data);
                                            ////back were you came
                                            setResult(RESULT_OK, intent);
                                            timer.cancel();
                                            finish();
                                        }


                                    }
                                }
                            });
                        }
                    },1,1000);
                }
                else
                {
                    Toast.makeText(G.context,"password and Confirmed password not matched",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
