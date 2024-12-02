package com.example.e_comerce.DatabaseAccess;

import com.example.e_comerce.JavaClasses.Customer;
import com.example.e_comerce.JavaClasses.date;
import com.example.e_comerce.JavaClasses.User;

public class CustomerDbAccess extends UserDbAccess {


    public User CheckUserExists(String username, String password)
    {
        Customer customer=new Customer("C mahmoud","123",new date(1,2,2000));
        return customer;
    }


}
