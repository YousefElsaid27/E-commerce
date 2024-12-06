package com.example.e_comerce.JavaClasses;

import java.io.Serializable;

public abstract class User implements Serializable {

    public String UserName ,Password,Email;

    public User(String Email,String Password)
    {
        this.Email=Email;
        this.Password=Password;
    }
    public User(String UserName,String Password,String email)
    {

        this.UserName=UserName;
        this.Password=Password;
        this.Email=email;

    }



}
