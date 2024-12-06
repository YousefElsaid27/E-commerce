package com.example.e_comerce.JavaClasses;

import android.graphics.Bitmap;

import java.util.jar.Attributes;

public class Product {


   public String Name;
   public Double Cost;
    public int Quantity,Id;
   public Bitmap Image ;


    public Product(int ID ,String Name,Double Cost,int Quantity,Bitmap imageBitMap )
    {
        this.Name=Name;
        this.Image=imageBitMap;
        this.Quantity=Quantity;
        this.Cost=Cost;


    }

}
