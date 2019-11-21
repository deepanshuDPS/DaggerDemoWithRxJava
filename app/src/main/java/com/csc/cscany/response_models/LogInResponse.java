package com.csc.cscany.response_models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "")
public class LogInResponse {

    @PrimaryKey(autoGenerate = true)
    int id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String message,otp;
    private boolean status;
}


