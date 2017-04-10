package com.akitsme.deevesoft.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akitsme.deevesoft.Model.Dist_model;
import com.akitsme.deevesoft.R;

import java.util.ArrayList;

/**
 * Created by AKASH on 09-04-2017.
 */

public class dist_Adapter extends RecyclerView.Adapter<dist_Adapter.myHolder> {

    public ArrayList<Dist_model> arrayList=new ArrayList<>();
    public dist_Adapter(ArrayList<Dist_model> arrayList){
        this.arrayList=arrayList;
    }
    @Override
    public dist_Adapter.myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.distance_row,parent,false);
        return new dist_Adapter.myHolder(view);
    }

    @Override
    public void onBindViewHolder(dist_Adapter.myHolder holder, int position) {
            holder.activity.setText(arrayList.get(position).activity);
            holder.distance.setText(arrayList.get(position).distance);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView distance,activity;
        public myHolder(View itemView) {
            super(itemView);
            distance=(TextView) itemView.findViewById(R.id.distance);
            activity=(TextView) itemView.findViewById(R.id.activity);
        }
    }
}
