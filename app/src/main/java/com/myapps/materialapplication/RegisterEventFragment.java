package com.myapps.materialapplication;


import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.myapps.materialapplication.Data.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterEventFragment extends Fragment implements AdapterView.OnItemSelectedListener {
//    public static final String eventlisturl="http://jsonplaceholder.typicode.com/posts";
    public static final String eventlisturl="http://192.168.87.50/tz-registration-master/events/get_all_events_mobile";

    private ArrayList<Integer> ids;
    private ArrayList<String> names;
    private int min;
    private ArrayList<Integer> mins;
    private int max;
    private ArrayList<Integer> maxs;

    private ProgressDialog progressDialog;
    Spinner spinner;

    private LinearLayout linearLayout;

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

        spinner=(Spinner)getActivity().findViewById(R.id.spinnerNewEvents);
        linearLayout=(LinearLayout)getActivity().findViewById(R.id.linearLayoutParticipants);
        new loadEvents().execute();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(),""+mins.get(i)+" - "+maxs.get(i),Toast.LENGTH_SHORT).show();
        min=mins.get(i);
        max=maxs.get(i);
        ((TextView)getActivity().findViewById(R.id.textViewMinimumParticipantsValue)).setText("" + min);
        ((TextView)getActivity().findViewById(R.id.textViewMaximumParticipantsValue)).setText(""+max);

        for(int j=0;j<min;j++){
            View view1=getActivity().getLayoutInflater().inflate(R.layout.edit_text_tz_id,null);
            linearLayout.addView(view1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class loadEvents extends AsyncTask<Void,Void,String[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("fetching event list..");
            progressDialog.setCancelable(false);
//            progressDialog.show();
        }

        @Override
        protected String[] doInBackground(Void... voids) {

            String jsonstr= Util.getStringFromURL(eventlisturl);
            if (jsonstr!=null) {
                Log.d("GOT FROM HTTP", jsonstr);
            }
//            JSONObject jsonObject=new JSONObject(jsonstr);
            try {
//                JSONObject jsonObject=new JSONObject(jsonstr);
//                JSONArray events=jsonObject.getJSONArray("events");
//
                JSONArray jsonArray=new JSONArray(jsonstr);
//                ArrayList<String> events=new ArrayList<>();
                int len=jsonArray.length();
                ids=new ArrayList<>();
                names=new ArrayList<>();
                mins=new ArrayList<>();
                maxs=new ArrayList<>();

                for(int i=0;i<len;i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    ids.add(jsonObject.getInt("eventid"));
                    names.add(jsonObject.getString("ename"));
                    mins.add(jsonObject.getInt("min"));
                    maxs.add(jsonObject.getInt("max"));

//                    events.add(jsonArray.getString(i));
                }
//                return (String[])names.toArray();
                String[] strings=new String[names.size()];
                for (int i=0;i<names.size();i++){
                    strings[i]=names.get(i);
                }
                return strings;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(RegisterEventFragment.this);
        }
    }
}
