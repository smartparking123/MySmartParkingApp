package com.example.smartparking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;  
import android.widget.RelativeLayout;  
import android.widget.TextView;  
import android.widget.Toast;


import com.example.smartparking.R;
import com.example.smartparking.models.Datum;

import java.util.List;


public class MyBookingListAdapter extends RecyclerView.Adapter<MyBookingListAdapter.ViewHolder>{
    private List<Datum> listdata;
  
   // RecyclerView recyclerView;  
    public MyBookingListAdapter (List<Datum> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.activity_layout_mybooking_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingListAdapter.ViewHolder holder, int position) {


        Datum datum = listdata.get(position);
        holder.txt_bookingid.setText(datum.getBId());
        holder.txt_name.setText(datum.getUName());
        holder.txt_date.setText(datum.getBDate());
        holder.txt_time.setText(datum.getBStartTime() + datum.getBEndTime());
        holder.txt_amount.setText(datum.getBAmount());
        holder.txt_placeid.setText(datum.getBPlaceid());

    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public TextView txt_bookingid,txt_name,txt_date,txt_time,txt_amount,txt_placeid;

        public ViewHolder(View itemView) {  
            super(itemView);  
            this.txt_bookingid = (TextView) itemView.findViewById(R.id.txt_bookingid);
            this.txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            this.txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            this.txt_time = (TextView) itemView.findViewById(R.id.txt_time);
            this.txt_amount = (TextView) itemView.findViewById(R.id.txt_amount);
            this.txt_placeid = (TextView) itemView.findViewById(R.id.txt_placeid);
        }
    }  
}  

