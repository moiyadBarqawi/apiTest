package edu.birzeit.myapplication;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ProductColors {
    @SerializedName("hex_value")
    String hex_value;
    @SerializedName("colour_name")
    String colour_name;

    @NonNull
    @Override
    public String toString() {
        return "hex_value: "+this.hex_value+" , colour_name :"+this.colour_name;
    }
}
