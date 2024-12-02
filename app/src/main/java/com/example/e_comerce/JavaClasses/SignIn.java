package com.example.e_comerce.JavaClasses;

import com.example.e_comerce.DatabaseAccess.UserDbAccess;

public class SignIn {


    private UserDbAccess UserDBManager;
    public SignIn( UserDbAccess UserDBManager)
    {
        this.UserDBManager = UserDBManager;
    }

    public User  Authenticate(String username,String password )
    {
        
        return UserDBManager.CheckUserExists(username,password);

    }





}
