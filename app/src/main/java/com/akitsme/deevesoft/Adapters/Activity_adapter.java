package com.akitsme.deevesoft.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akitsme.deevesoft.Model.Activities_model;
import com.akitsme.deevesoft.R;

import java.util.ArrayList;

/**
 * Created by AKASH on 09-04-2017.
 */

public class Activity_adapter extends RecyclerView.Adapter<Activity_adapter.myHolder> {
    public ArrayList<Activities_model> arrayList=new ArrayList<>();
    public Activity_adapter(ArrayList<Activities_model> arrayList){
        this.arrayList=arrayList;
    }

    @Override
    public Activity_adapter.myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activities,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(Activity_adapter.myHolder holder, int position) {
        holder.cal.setText(arrayList.get(position).getCalories());
        holder.name.setText(arrayList.get(position).getName());
        holder.des.setText(arrayList.get(position).getDescription());
        holder.dis.setText(arrayList.get(position).getDistance());
        holder.dur.setText(arrayList.get(position).getDuration());
        holder.start.setText(arrayList.get(position).getStarttime());
        holder.step.setText(arrayList.get(position).getSteps());
        if(position==arrayList.size()-1){
            holder.status.setText("No More Ahead");
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView status;
        TextView cal,des,dis,dur,start,step,name;
        public myHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nameee) ;
            status= (TextView) itemView.findViewById(R.id.status);
            cal=(TextView) itemView.findViewById(R.id.valcal);
            des=(TextView) itemView.findViewById(R.id.valdes);
            dis=(TextView) itemView.findViewById(R.id.valdis);
            dur=(TextView) itemView.findViewById(R.id.valdur);
            start=(TextView) itemView.findViewById(R.id.valstart);
            step=(TextView) itemView.findViewById(R.id.valsteps);
        }
    }
}
