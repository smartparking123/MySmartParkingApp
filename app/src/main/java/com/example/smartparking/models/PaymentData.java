package com.example.smartparking.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentData {
    @SerializedName("P_id")
    @Expose
    private String pId;
    @SerializedName("P_date")
    @Expose
    private String pDate;
    @SerializedName("P_time")
    @Expose
    private String pTime;
    @SerializedName("U_id")
    @Expose
    private String uId;
    @SerializedName("P_status")
    @Expose
    private String pStatus;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("B_id")
    @Expose
    private String bId;

    @SerializedName("P_amount")
    @Expose
    private String pAmount;

    public String getpAmount() {
        return pAmount;

    }

    public void setpAmount(String pId) {
        this.pAmount = pAmount;
    }

    public String getPId() {
        return pId;

    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getPDate() {
        return pDate;
    }

    public void setPDate(String pDate) {
        this.pDate = pDate;
    }

    public String getPTime() {
        return pTime;
    }

    public void setPTime(String pTime) {
        this.pTime = pTime;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getPStatus() {
        return pStatus;
    }

    public void setPStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

}
