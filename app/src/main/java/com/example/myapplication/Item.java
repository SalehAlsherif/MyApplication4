package com.example.myapplication;

import android.graphics.Bitmap;
import android.util.Log;

public class Item {
     String Title;
     String Price ;
    String Quantity ;
    String Expiry ;
     Bitmap Image;

    public String getExpiry() {
        return Expiry;
    }

    public void setExpiry(String expiry) {
        Expiry = expiry;
    }

    public Item(String t, String p, String q, Bitmap th, String e){
        Title=t;
        Price=p;
        Quantity=q;
        Image =th;
        Expiry=e;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap thumbnail) {
        Image = thumbnail;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
