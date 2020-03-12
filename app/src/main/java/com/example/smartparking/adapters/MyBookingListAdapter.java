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

import java.text.SimpleDateFormat;
import java.util.Date;
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
        holder.txt_bookingid.setText("Booking ID: "+datum.getBId());
        holder.txt_name.setText("Name: "+datum.getUName());
        holder.txt_date.setText("Date: "+ parseDate(datum.getBDate()));
        holder.txt_time.setText("Time: "+parseTime(datum.getBStartTime())+" to " + parseTime(datum.getBEndTime()));
        holder.txt_amount.setText("Amount: "+datum.getBAmount());
        holder.txt_placeid.setText("Place ID: "+datum.getBPlaceid());

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


    public String parseDate(String dt) {

        try {

            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");

            Date date = input.parse(dt);

            return output.format(date);


        }
        catch (Exception e) {
            e.printStackTrace();

            return dt;
        }


    }


    public String parseTime(String dt) {

        try {

            SimpleDateFormat input = new SimpleDateFormat("HH:mm");

            SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");

            Date date = input.parse(dt);

            return output.format(date);


        }
        catch (Exception e) {
            e.printStackTrace();

            return dt;
        }


    }
}  

