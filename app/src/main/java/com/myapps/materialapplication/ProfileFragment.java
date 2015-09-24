package com.myapps.materialapplication;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.myapps.materialapplication.Data.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by poliveira on 11/03/2015.
 */
public class ProfileFragment extends Fragment {
    public static final String TAG = "stats";
    public static final String profileUrl="http://192.168.87.50/tz-registration-master/profile/index_mobile/9346472";
//    public static final String profileUrl="http://bhuichalo.com/tz15/profile.json";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new LoadEventsTask().execute();
    }

    public class LoadEventsTask extends AsyncTask<Void,Void,HashMap<String ,String>> {

        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("fetching profile data..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected HashMap<String ,String> doInBackground(Void... voids) {

            String jsonstr= Util.getStringFromURL(profileUrl);
            if (jsonstr!=null) {
                Log.d("GOT FROM HTTP", jsonstr);
                try {
                    JSONObject jsonObject=new JSONObject(jsonstr);

                    HashMap<String,String> hashMap=new HashMap<>();

                    hashMap.put("userid",jsonObject.getString("userid"));
                    if(jsonObject.getString("registration").equals("0")) {
                        hashMap.put("registration", "₹ 400 unpaid");
                    }else {
                        hashMap.put("registration", "paid");
                    }
                    if(jsonObject.getString("hospitality").equals("0")) {
                        hashMap.put("hospitality", "₹ 600 unpaid");
                    }else {
                        hashMap.put("hospitality", "paid");
                    }
                    hashMap.put("name",jsonObject.getString("name"));
                    hashMap.put("collegeid",jsonObject.getString("collegeid"));
                    hashMap.put("college",jsonObject.getString("college"));
                    hashMap.put("phone",jsonObject.getString("phone"));
                    hashMap.put("email",jsonObject.getString("email"));

                    return hashMap;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//            JSONObject jsonObject=new JSONObject(jsonstr);
            return null;
        }

        @Override
        protected void onPostExecute(HashMap<String ,String> hashMap) {
            super.onPostExecute(hashMap);
            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
            if (hashMap==null) {
                Toast.makeText(getActivity(), "Could not fetch events, please try again", Toast.LENGTH_SHORT).show();
            } else{
                ((TextView)getActivity().findViewById(R.id.textViewTzIdValue)).setText(hashMap.get("userid"));
                if (hashMap.get("registration").equalsIgnoreCase("paid")){
                    //TODO SAVE THINGS IN SHAREDPREFERENCES
                    getActivity().findViewById(R.id.imageViewQrCode).setVisibility(View.VISIBLE);
                }
                ((TextView)getActivity().findViewById(R.id.textViewTechnozionRegistrationPaid)).setText(hashMap.get("registration"));
                ((TextView)getActivity().findViewById(R.id.textViewHospitalityRegistrationPaid)).setText(hashMap.get("hospitality"));
                ((TextView)getActivity().findViewById(R.id.textViewName)).setText(hashMap.get("name"));
                ((TextView)getActivity().findViewById(R.id.textViewCollegeIdValue)).setText(hashMap.get("collegeid"));
                ((TextView)getActivity().findViewById(R.id.textViewPhoneNumber)).setText(hashMap.get("phone"));
                ((TextView)getActivity().findViewById(R.id.textViewEmail)).setText(hashMap.get("email"));
            }
        }
    }

}
