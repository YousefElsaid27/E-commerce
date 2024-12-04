package com.example.e_comerce.DatabaseAccess;

import com.example.e_comerce.JavaClasses.Customer;
import com.example.e_comerce.JavaClasses.date;
import com.example.e_comerce.JavaClasses.User;

public class CustomerDbAccess extends UserDbAccess {


    public User CheckUserExists(String username, String password)
    {
        return null;
    }


    public boolean registerCustomer(String username, String email, String password, String birthdate) {
        return true;
    }
}
