package com.akitsme.deevesoft.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akitsme.deevesoft.Adapters.weight_adapter;
import com.akitsme.deevesoft.Model.wegiht_model;
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

import java.util.ArrayList;

/**
 * Created by AKASH on 10-04-2017.
 */

public class weight_fragment extends Fragment {
    public RecyclerView wr;
    public RecyclerView.LayoutManager layoutManager;
    JsonObjectRequest request;
    public int flag=0;
    RequestQueue mRequestQueue;
    public String b,w,d,t,n;
    public ArrayList<wegiht_model> arrayList=new ArrayList<>();

    public static String url="https://firebasestorage.googleapis.com/v0/b/samplechatapp-31da2.appspot.com/o/getWeight.json?alt=media&token=373d69de-65d7-4463-9a2b-1844e8320e6e";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.weight, container, false);

        wr= (RecyclerView) view.findViewById(R.id.activities);
        layoutManager =new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        wr.setLayoutManager(layoutManager);

        mRequestQueue= VolleySingleton.getInstance().getmRequestQueue();
        request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray json3=response.getJSONArray("weight");
                            for(int i=0;i<json3.length();i++) {
                                JSONObject json=json3.getJSONObject(i);
                                n=json.getString("source");
                                b=json.getString("bmi");
                                w=json.getString("weight");
                                d=json.getString("date");
                                t=json.getString("time");
                                arrayList.add(new wegiht_model(b,w,d,t,n));
                                if(flag==0) {
                                    wr.setAdapter(new weight_adapter(arrayList));
                                    flag = 1;
                                }
                                wr.getAdapter().notifyItemInserted(i);
                            }
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
