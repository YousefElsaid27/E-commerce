package com.example.e_comerce.JavaClasses;

public class Customer extends User {
    public Date dateOfBirth;


    public Customer(String UserName, String Password, Date dateOfBirth) {
        super(UserName, Password);
        this.dateOfBirth = dateOfBirth;
    }





}
