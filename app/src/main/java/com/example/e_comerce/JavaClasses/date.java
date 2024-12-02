package com.example.e_comerce.JavaClasses;

import java.io.Serializable;

public class date implements Serializable {
    public int day;
    public int month;
    public int year;

    public date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }



}



