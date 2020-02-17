package com.example.smartparking.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;  
import android.widget.Toast;

import com.example.smartparking.Booking;
import com.example.smartparking.R;
import com.example.smartparking.utils.Constants;

import java.util.HashMap;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{  
    private String[] listdata;

    int index = -1;

    Context mContext;

    setOnItemClickListner mItemClickListner;

   // RecyclerView recyclerView;  
    public MyListAdapter(Context context , String[] listdata,setOnItemClickListner itemlistener) {
        this.mContext = context;
        this.listdata = listdata;
        this.mItemClickListner = itemlistener;
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_panel_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }

    public interface setOnItemClickListner{

        public void onItemClick(int pos,String s);

    }


  
    @Override  
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String myListData = listdata[position];
        holder.txt_panel_no.setText(listdata[position]);
        holder.img_panel_car.setImageResource(R.drawable.auto);

        if (index == position) {

            holder.txt_panel_no.setTextColor(Color.BLUE);
            holder.img_panel_car.getDrawable().setTint(Color.BLUE);

        }
        else {
            holder.txt_panel_no.setTextColor(Color.LTGRAY);
            holder.img_panel_car.getDrawable().setTint(Color.LTGRAY);
        }


        holder.main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index = position;
                notifyDataSetChanged();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mItemClickListner != null){
                            mItemClickListner.onItemClick(position,holder.txt_panel_no.getText().toString());
                        }

                    }
                },500);

            }
        });
    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.length;  
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  
        public ImageView img_panel_car;
        public TextView txt_panel_no;
        LinearLayout main_layout;
        public ViewHolder(View itemView) {  
            super(itemView);  
            this.img_panel_car = (ImageView) itemView.findViewById(R.id.img_panel_car);
            this.txt_panel_no = (TextView) itemView.findViewById(R.id.txt_panel_no);
            this.main_layout = (LinearLayout) itemView.findViewById(R.id.main_layout);
        }  
    }  
}  
