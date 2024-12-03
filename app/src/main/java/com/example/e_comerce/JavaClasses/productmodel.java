package com.example.e_comerce.JavaClasses;

public class productmodel {

    String title;
    String describe;
    int icon;

    public productmodel(String title, String describe, int icon) {
        this.title = title;
        this.describe = describe;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescribe() {
        return describe;
    }

    public int getIcon() {
        return icon;
    }
}
