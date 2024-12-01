package com.example.e_comerce.JavaClasses;

public class AdminAuthentication implements IUserAuthentication {
    boolean isAuthenticated=false;
    public User authenticate(String username, String password)  {

        //  checkAdminExists(username,password);


        //GetAdminData();

        //test
        Admin admin=new Admin("Admin mahmoud","123");

        return admin;
    }
}
