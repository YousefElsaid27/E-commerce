package com.example.e_comerce.JavaClasses;

public class mylist {
    String  name;
    int imag;

    public mylist(String title, int icon) {
        this.name = title;
        this.imag = icon;
    }

    public String getTitle() {
        return this.name;
    }

    public int getIcon() {
        return this.imag;
    }
}
