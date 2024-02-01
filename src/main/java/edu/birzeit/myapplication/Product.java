package edu.birzeit.myapplication;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    int ID;
    @SerializedName("brand")
    String brand;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    double price;
    @SerializedName("product_type")
    String product_type;
//    @SerializedName("product_colors")
//    ProductColors product_colors[];

//    public ProductColors[] getProduct_colors() {
//        return product_colors;
//    }


    @NonNull
    @Override
    public String toString() {

        return "id : "+this.ID +" ,Brand : "+this.brand+" , Name : "+this.name +"Price : "+this.price+"$ ,Product_type : "+this.product_type +"\n"  ;
    }

}
