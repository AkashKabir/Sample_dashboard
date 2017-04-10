package com.akitsme.deevesoft.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akitsme.deevesoft.R;
import com.akitsme.deevesoft.Model.wegiht_model;

import java.util.ArrayList;

/**
 * Created by AKASH on 10-04-2017.
 */

public class weight_adapter extends RecyclerView.Adapter<weight_adapter.myHolder> {
    ArrayList<wegiht_model> arrayList=new ArrayList<>();

    public weight_adapter(ArrayList<wegiht_model> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public weight_adapter.myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_row,parent,false);
        return new weight_adapter.myHolder(view);
    }

    @Override
    public void onBindViewHolder(weight_adapter.myHolder holder, int position) {
        holder.name.setText(arrayList.get(position).bmi);
        holder.status.setText(arrayList.get(position).weight);
        holder.cal.setText(arrayList.get(position).date);
        holder.des.setText(arrayList.get(position).time);
        holder.name2.setText(arrayList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView status;
        TextView cal,des,dis,dur,start,step,name,name2;
        public myHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.valcal) ;
            status= (TextView) itemView.findViewById(R.id.valdes);
            cal=(TextView) itemView.findViewById(R.id.valdis);
            des=(TextView) itemView.findViewById(R.id.valdur);
            name2=(TextView)itemView.findViewById(R.id.nameee);
        }
    }
}
