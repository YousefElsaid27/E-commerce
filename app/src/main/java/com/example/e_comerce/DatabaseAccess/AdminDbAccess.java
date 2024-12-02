package com.example.e_comerce.DatabaseAccess;

import com.example.e_comerce.JavaClasses.Admin;
import com.example.e_comerce.JavaClasses.User;

public class AdminDbAccess extends UserDbAccess {

    public  User CheckUserExists(String username, String password)
    {
        Admin admin = new Admin("A mahmoud", "123");
        return admin;
    }
}
