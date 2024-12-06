package com.example.e_comerce.JavaClasses;

import com.example.e_comerce.DatabaseAccess.DbUserAccses;

public class SignIn {


    private DbUserAccses UserDBManager;
    public SignIn( DbUserAccses UserDBManager)
    {
        this.UserDBManager = UserDBManager;
    }

    public User  Authenticate(String username,String password )
    {
        
        return UserDBManager.CheckUserExists(username,password);

    }





}
