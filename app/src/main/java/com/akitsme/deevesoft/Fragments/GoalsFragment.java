package com.akitsme.deevesoft.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akitsme.deevesoft.R;
import com.akitsme.deevesoft.helper.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by AKASH on 09-04-2017.
 */

public class GoalsFragment extends Fragment {

    JsonObjectRequest request;
    RequestQueue mRequestQueue;
    TextView cal,dis,st,fl;
    public static String url="https://firebasestorage.googleapis.com/v0/b/samplechatapp-31da2.appspot.com/o/getDailyActivity.json?alt=media&token=f187e4de-2a5e-43a7-9a6f-329140b3f4a2";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view=inflater.inflate(R.layout.goals, container, false);

        cal= (TextView) view.findViewById(R.id.CaloriesprogressBar);
        dis= (TextView) view.findViewById(R.id.distanceprogressBar);
        st= (TextView) view.findViewById(R.id.StepsprogressBar);
        fl= (TextView) view.findViewById(R.id.floorprogressBar);


        mRequestQueue= VolleySingleton.getInstance().getmRequestQueue();
        request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject json3=response.getJSONObject("goals");
                            cal.setText(json3.getString("caloriesOut"));
                            dis.setText(json3.getString("distance"));
                            fl.setText(json3.getString("floors"));
                            st.setText(json3.getString("steps"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(request);
        return view;
    }
}
