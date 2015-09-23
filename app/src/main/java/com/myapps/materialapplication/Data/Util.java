package com.myapps.materialapplication.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by John on 9/21/2015.
 */
public class Util {

    public static String getStringFromURLWithPost(String s){
        try {
            URL url=new URL(s);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.connect();
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("eventid", "12");

            String values = "jsonValue="+ URLEncoder.encode(jsonParam.toString(),"UTF-8");
           // String userid;
            //String param = "9346472";
            //String param= "userid=" + URLEncoder.encode("9346472","UTF-8")+"&userid1=" + URLEncoder.encode("9346472","UTF-8");
            OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
            out.write(values);
            out.close();



            int HttpResult =httpURLConnection.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK) {


                InputStream is = httpURLConnection.getInputStream();
//            InputStream is=urlConnection.getInputStream();


                BufferedReader reader = new BufferedReader(new InputStreamReader(is));


                String line = null;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {

                    builder.append(line);
                }
                Log.d("checking_val",builder.toString());
                return builder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }




    public static String getStringFromURL(String s) {
        try {
            URL url=new URL(s);
//            HttpURLConnection htt
//            URLConnection urlConnection=url.openConnection();
//            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(false);
//
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
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
