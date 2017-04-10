package com.akitsme.deevesoft.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akitsme.deevesoft.Adapters.dist_Adapter;
import com.akitsme.deevesoft.Model.Dist_model;
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
import java.util.ArrayList;

/**
 * Created by AKASH on 09-04-2017.
 */

public class SummaryFrag extends Fragment {
    public RecyclerView dist;
    JsonObjectRequest request;
    public RecyclerView.LayoutManager layoutManager;
    RequestQueue mRequestQueue;
    CardView mincard,calcard;
    double sum=0,dis;
    public int flag=0,flag1=0,flag2=0,flag3=0;
    public TextView vco,vcoact,vcobmr,vcomarg,vdistfloor,vdiststeps,vdistele,vminfa,vminla,vminsa,vminva;
    public String co,coact,cobmr,comarg,distfloor,diststeps,distele,minfa,minla,minsa,minva;
    public String acitivity,distance;
    public ArrayList<Dist_model> arrayList2=new ArrayList<>();
    public RelativeLayout minsum,dissum,calsum;
    ProgressBar progressBar;
    float min=0;
    TextView calsumval,dissumval,minvaltxt;
    DecimalFormat df;
    public static String url="https://firebasestorage.googleapis.com/v0/b/samplechatapp-31da2.appspot.com/o/getDailyActivity.json?alt=media&token=f187e4de-2a5e-43a7-9a6f-329140b3f4a2";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.summary, container, false);
        dist= (RecyclerView)view.findViewById(R.id.distancesRecycler);

        vdistfloor= (TextView) view.findViewById(R.id.distfloor);
        vdiststeps= (TextView) view.findViewById(R.id.diststeps);
        vdistele= (TextView) view.findViewById(R.id.distele);
        vminfa= (TextView) view.findViewById(R.id.minfa);
        vminla= (TextView) view.findViewById(R.id.minla);
        vminsa= (TextView) view.findViewById(R.id.minsa);
        vminva= (TextView) view.findViewById(R.id.minva);

        minsum=(RelativeLayout)view.findViewById(R.id.minsum);
        dissum= (RelativeLayout) view.findViewById(R.id.dissum);
        calsum= (RelativeLayout) view.findViewById(R.id.calsum);

        minvaltxt= (TextView) view.findViewById(R.id.minsumtext);
        dissumval= (TextView) view.findViewById(R.id.dissumtext);
        calsumval= (TextView) view.findViewById(R.id.caltext);

        mincard=(CardView)view.findViewById(R.id.mincard);
        calcard=(CardView)view.findViewById(R.id.caloriescard);

        progressBar= (ProgressBar) view.findViewById(R.id.CaloriesprogressBar2);
        vcoact= (TextView) view.findViewById(R.id.distanceprogressBar);
        vco=(TextView)view.findViewById(R.id.textsffd);
        vcomarg= (TextView) view.findViewById(R.id.margCaloriesprogressBar);
        vcobmr= (TextView) view.findViewById(R.id.StepsprogressBar);

        layoutManager =new LinearLayoutManager(getContext());
        dist.setLayoutManager(layoutManager);

        mRequestQueue= VolleySingleton.getInstance().getmRequestQueue();
        request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject json = response.getJSONObject("summary");
                                JSONArray read = json.getJSONArray("distances");
                                coact=json.getString("activityCalories");
                                cobmr=json.getString("caloriesBMR");
                                co=json.getString("caloriesOut");
                                distele=json.getString("elevation");
                                minfa=json.getString("fairlyActiveMinutes");
                                distfloor=json.getString("floors");
                                minla=json.getString("lightlyActiveMinutes");
                                comarg=json.getString("marginalCalories");
                                diststeps=json.getString("steps");
                                minva=json.getString("veryActiveMinutes");
                                minsa=json.getString("sedentaryMinutes");

                                JSONObject json3=response.getJSONObject("goals");
                                int totalco=json3.getInt("caloriesOut");
                                int usedca=json.getInt("caloriesOut");

                                float percentage=((float)usedca/(float)totalco)*100;
                                progressBar.setProgress((int)percentage);

                                vco.setText(co+" "+percentage+"%");
                                vcoact.setText(coact);
                                vcobmr.setText(cobmr);
                                vcomarg.setText(comarg);

                                min=Integer.parseInt(minfa)+Integer.parseInt(minsa)+Integer.parseInt(minva)
                                +Integer.parseInt(minla);
                                minvaltxt.setText("Toatal active min: "+min);
                                min=0;

                                vdistfloor.setText(distfloor);
                                vdiststeps.setText(diststeps);
                                vdistele.setText(distele);
                                vminfa.setText(minfa);
                                vminla.setText(minla);
                                vminsa.setText(minsa);
                                vminva.setText(minva);

                                int temp=totalco-usedca;
                                calsumval.setText(usedca+" Calories Burned ,"+temp+" cal left");



                            for(int i=0;i<read.length();i++){

                                JSONObject json2=read.getJSONObject(i);
                                distance=json2.getString("distance");
                                sum=sum+Double.parseDouble(distance);
                                df = new DecimalFormat("#.00");
                                acitivity=json2.getString("activity");
                                arrayList2.add(new Dist_model(acitivity,distance));
                                if(flag==0) {
                                    dist.setAdapter(new dist_Adapter(arrayList2));
                                    flag = 1;
                                }
                                dist.getAdapter().notifyItemInserted(i);
                            }
                            dissumval.setText("Total distance: "+df.format(sum));
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


        minsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1==0){
                    mincard.setVisibility(View.VISIBLE);
                    flag1=1;
                }
                else if(flag1==1){
                    mincard.setVisibility(View.GONE);
                    flag1=0;
                }
            }
        });

        calsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==0){
                    calcard.setVisibility(View.VISIBLE);
                    flag2=1;
                }
                else if(flag2==1){
                    calcard.setVisibility(View.GONE);
                    flag2=0;
                }
            }
        });

        dissum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==0){
                    dist.setVisibility(View.VISIBLE);
                    flag2=1;
                }
                else if(flag2==1){
                    dist.setVisibility(View.GONE);
                    flag2=0;
                }
            }
        });
        return view;
    }
}
