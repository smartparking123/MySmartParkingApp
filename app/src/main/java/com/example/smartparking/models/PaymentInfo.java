package com.example.smartparking.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentInfo {

    @SerializedName("data")
    @Expose
    private List<PaymentData> data = null;

    public List<PaymentData> getData() {
        return data;
    }
}

