package com.myapps.materialapplication;


import android.app.VoiceInteractor;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.myapps.materialapplication.Data.Util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterEventFragment extends Fragment {
    public static final String eventlisturl="http://jsonplaceholder.typicode.com/posts";

    Spinner spinner;

    public RegisterEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_event, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new loadEvents().execute();


        spinner=(Spinner)getActivity().findViewById(R.id.spinnerNewEvents);

    }
    public class loadEvents extends AsyncTask<Void,Void,String[]>{

        @Override
        protected String[] doInBackground(Void... voids) {

            String jsonstr= Util.getStringFromURL(eventlisturl);
//            JSONObject jsonObject=new JSONObject(jsonstr);
            Log.d("GOT FROM HTTP",jsonstr);
            String[]array=new String[]{
                    "H","E","L","L","O"
            };
            return array;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings);
            spinner.setAdapter(arrayAdapter);
        }
    }
}
