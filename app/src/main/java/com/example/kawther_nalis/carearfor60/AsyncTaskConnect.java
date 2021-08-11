package com.example.kawther_nalis.carearfor60;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by kawther_nalis on 10/30/2017.
 */

public class AsyncTaskConnect extends AsyncTask {
    public String link="";
    public String email;
    public String pass;

    public AsyncTaskConnect(String link,String email,String pass)
    {
        this.link=link;
        this.email=email;
        this.pass=pass;
    }
    @Override
    protected Object doInBackground(Object[] objects) {




        try
        {

            //////////////setting  email-pass to server
            String data= URLEncoder.encode("email","UTF8")+"="+URLEncoder.encode(email,"UTF8");
            data+="&"+URLEncoder.encode("pass","UTF8")+"="+URLEncoder.encode(pass,"UTF8");
            /////////////connection to server
            URL url=new URL(link);
            URLConnection connection=url.openConnection();
            ///////////sending data to server
            connection.setDoOutput(true);
            OutputStreamWriter writer= new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();





            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder= new StringBuilder();

            String line=null;
            while ((line=reader.readLine())!=null)
            {
                builder.append(line);
            }
            SignIn.data=builder.toString();

        }
        catch (Exception e)
        {

        }
        return null;

    }
}
