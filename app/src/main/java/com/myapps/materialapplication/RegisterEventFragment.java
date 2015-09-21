package com.myapps.materialapplication;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.myapps.materialapplication.Data.Util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterEventFragment extends Fragment {

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
        String[]array=new String[]{
                "H","E","L","L","O"
        };
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
        spinner.setAdapter(arrayAdapter);
    }
    public class loadEvents extends AsyncTask<Void,Void,String[]>{

        @Override
        protected String[] doInBackground(Void... voids) {
            String jsonstr= Util.getStringFromURL("http://www.google.com/");
//            JSONObject jsonObject=new JSONObject(jsonstr);
            Log.d("GOT FROM HTTP",jsonstr);
            return new String[0];
        }
    }
}
