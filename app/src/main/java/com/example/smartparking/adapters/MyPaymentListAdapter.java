package com.example.smartparking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartparking.R;
import com.example.smartparking.models.Datum;
import com.example.smartparking.models.PaymentData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MyPaymentListAdapter extends RecyclerView.Adapter<MyPaymentListAdapter.ViewHolder>{
    private List<PaymentData> listdata;

   // RecyclerView recyclerView;
    public MyPaymentListAdapter(List<PaymentData> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout_payment_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPaymentListAdapter.ViewHolder holder, int position) {


        PaymentData paymentData = listdata.get(position);

        holder.txt_date.setText("Date and Time: "+ parseDate(paymentData.getPDate())+" and "+ parseTime(paymentData.getPTime()));
        holder.txt_amount.setText("Amount: "+paymentData.getpAmount());
        holder.transaction_id.setText("Transaction ID: "+paymentData.getTransactionId());

    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public TextView txt_date,txt_amount,transaction_id;

        public ViewHolder(View itemView) {  
            super(itemView);  

            this.txt_date = (TextView) itemView.findViewById(R.id.txt_date);

            this.txt_amount = (TextView) itemView.findViewById(R.id.txt_amount);
            this.transaction_id = (TextView) itemView.findViewById(R.id.transaction_id);
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

