package com.example.e_comerce.JavaClasses;

public class SignIn {

   private IUserAuthentication userAuthentication;

   public SignIn(IUserAuthentication userAuthentication)
   {
       this.userAuthentication = userAuthentication;
   }
   public User Authenticate(String username,String password )
    {
    return userAuthentication.authenticate(username,password);

    }




}
