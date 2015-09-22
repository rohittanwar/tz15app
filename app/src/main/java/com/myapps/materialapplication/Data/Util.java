package com.myapps.materialapplication.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by John on 9/21/2015.
 */
public class Util {

    public static String getStringFromURL(String s) {
        try {
            URL url=new URL(s);
//            HttpURLConnection htt
//            URLConnection urlConnection=url.openConnection();
//            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(false);
//
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            InputStream is=httpURLConnection.getInputStream();
//            InputStream is=urlConnection.getInputStream();

            BufferedReader reader=new BufferedReader(new InputStreamReader(is));


            String line=null;
            StringBuilder builder=new StringBuilder();
            while((line=reader.readLine())!=null){
                builder.append(line);
            }
            return builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
