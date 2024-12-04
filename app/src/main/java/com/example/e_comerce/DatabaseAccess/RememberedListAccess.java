package com.example.e_comerce.DatabaseAccess;

import com.example.e_comerce.JavaClasses.User;

import java.util.ArrayList;
import java.util.List;

public class RememberedListAccess {

    List<RememberedUser> rememberedUsers;

    public RememberedListAccess()
    {

        rememberedUsers=new ArrayList<>();
    }

    public List<RememberedUser> GetRememberedUsers()
    {


         rememberedUsers.add(new RememberedUser("No Remembered users yet", ""));
        return rememberedUsers;

    }


    public boolean AddRememberedUser(User user)
    {
        //function to push user to database

        return true;
    }
}
