package com.example.e_comerce.DatabaseAccess;

import com.example.e_comerce.JavaClasses.User;

public abstract class DbUserAccses {



        public abstract User CheckUserExists(String username, String password);


}
