package com.myapps.materialapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
 * Created by karnika_ag on 9/23/2015.
 */
public class RegisterWorkshopFragment extends Fragment implements AdapterView.OnItemSelectedListener  {

    public static final String eventlisturl="http://192.168.87.50/tz-registration-master/workshops/get_all_workshops_mobile";
    Spinner spinner;
    private LinearLayout linearLayout;

    private ArrayList<Integer> ids;
    private ArrayList<String> names;
    private int cost;
    private ArrayList<Integer> costs;
    private int min;
    private ArrayList<Integer> mins;
    private int max;
    private ArrayList<Integer> maxs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_workshop, container, false);
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
        cost=costs.get(i);
        ((TextView)getActivity().findViewById(R.id.textViewMinimumParticipantsValue)).setText("" + min);
        ((TextView)getActivity().findViewById(R.id.textViewMaximumParticipantsValue)).setText(""+max);
        ((TextView)getActivity().findViewById(R.id.textViewWorkshopCostValue)).setText(""+cost);
        linearLayout.removeAllViews();
        for(int j=0;j<min;j++){
            View view1=getActivity().getLayoutInflater().inflate(R.layout.edit_text_tz_id,null);
            ((EditText)view1).setHint("*Enter Tz-id");
            linearLayout.addView(view1);
        }
        for(int j=min;j<max;j++){
            View view1=getActivity().getLayoutInflater().inflate(R.layout.edit_text_tz_id,null);
            ((EditText)view1).setHint("Enter Tz-id");
            linearLayout.addView(view1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class loadEvents extends AsyncTask<Void,Void,String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String[] doInBackground(Void... voids) {

            String jsonstr= Util.getStringFromURLWithPost(eventlisturl);
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
                costs=new ArrayList<>();

                for(int i=0;i<len;i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    ids.add(jsonObject.getInt("workshopid"));
                    names.add(jsonObject.getString("wname"));
                    mins.add(jsonObject.getInt("min"));
                    maxs.add(jsonObject.getInt("max"));
                    costs.add(jsonObject.getInt("cost"));

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
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(RegisterWorkshopFragment.this);
        }
    }
}
