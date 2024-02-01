package edu.birzeit.myapplication;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("message")
    String message;
    String status;
    @SerializedName("status")

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

}
