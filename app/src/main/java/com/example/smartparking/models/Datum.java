package com.example.smartparking.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("B_id")
@Expose
private String bId;
@SerializedName("U_id")
@Expose
private String uId;
@SerializedName("B_date")
@Expose
private String bDate;
@SerializedName("B_placeid")
@Expose
private String bPlaceid;
@SerializedName("B_amount")
@Expose
private String bAmount;
@SerializedName("B_start_time")
@Expose
private String bStartTime;
@SerializedName("B_end_time")
@Expose
private String bEndTime;
@SerializedName("B_status")
@Expose
private String bStatus;
@SerializedName("B_duration")
@Expose
private String bDuration;
@SerializedName("U_name")
@Expose
private String uName;
@SerializedName("U_contactno")
@Expose
private String uContactno;

public String getBId() {
return bId;
}

public void setBId(String bId) {
this.bId = bId;
}

public String getUId() {
return uId;
}

public void setUId(String uId) {
this.uId = uId;
}

public String getBDate() {
return bDate;
}

public void setBDate(String bDate) {
this.bDate = bDate;
}

public String getBPlaceid() {
return bPlaceid;
}

public void setBPlaceid(String bPlaceid) {
this.bPlaceid = bPlaceid;
}

public String getBAmount() {
return bAmount;
}

public void setBAmount(String bAmount) {
this.bAmount = bAmount;
}

public String getBStartTime() {
return bStartTime;
}

public void setBStartTime(String bStartTime) {
this.bStartTime = bStartTime;
}

public String getBEndTime() {
return bEndTime;
}

public void setBEndTime(String bEndTime) {
this.bEndTime = bEndTime;
}

public String getBStatus() {
return bStatus;
}

public void setBStatus(String bStatus) {
this.bStatus = bStatus;
}

public String getBDuration() {
return bDuration;
}

public void setBDuration(String bDuration) {
this.bDuration = bDuration;
}

public String getUName() {
return uName;
}

public void setUName(String uName) {
this.uName = uName;
}

public String getUContactno() {
return uContactno;
}

public void setUContactno(String uContactno) {
this.uContactno = uContactno;
}

}