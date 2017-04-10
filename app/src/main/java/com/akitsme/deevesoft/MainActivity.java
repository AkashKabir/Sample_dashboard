package com.akitsme.deevesoft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.akitsme.deevesoft.Adapters.Activity_adapter;
import com.akitsme.deevesoft.Model.Activities_model;
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

public class MainActivity extends AppCompatActivity {
    public RecyclerView act,goals;
    JsonObjectRequest request;
    public RecyclerView.LayoutManager layoutManager;
    RequestQueue mRequestQueue;
    public int flag=0;
    public String calories,description,distance,duration,starttime,steps,name;
    public ArrayList<Activities_model> arrayList=new ArrayList<>();
    public static String url="https://firebasestorage.googleapis.com/v0/b/samplechatapp-31da2.appspot.com/o/getDailyActivity.json?alt=media&token=f187e4de-2a5e-43a7-9a6f-329140b3f4a2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        act= (RecyclerView) findViewById(R.id.activities);
        layoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        act.setLayoutManager(layoutManager);
        mRequestQueue= VolleySingleton.getInstance().getmRequestQueue();
            request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                                JSONArray read = response.getJSONArray("activities");

                                for(int i=0;i<read.length();i++) {
                                    JSONObject json = read.getJSONObject(i);
                                    calories=json.getString("calories");
                                    description=json.getString("description");
                                    distance=json.getString("distance");
                                    duration=json.getString("duration");
                                    starttime=json.getString("startTime");
                                    name=json.getString("name");
                                    steps=json.getString("steps");
                                    arrayList.add(new Activities_model(calories,description,distance,duration,starttime,steps,name));
                                    if(flag==0) {
                                        act.setAdapter(new Activity_adapter(arrayList));
                                        flag = 1;
                                    }
                                    act.getAdapter().notifyItemInserted(i);
                                }
                                    Toast.makeText(MainActivity.this, "DATA RECIEVED\n"+read.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Artist no Connection", Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(request);

    }
}
