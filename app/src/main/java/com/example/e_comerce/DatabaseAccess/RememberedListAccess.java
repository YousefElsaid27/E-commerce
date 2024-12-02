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

        //Get it from the database but this data for testing
        rememberedUsers.add(new RememberedUser("A mahmoud", "123"));
        rememberedUsers.add(new RememberedUser("C mahmoud", "123"));

        return rememberedUsers;

    }


    public boolean AddRememberedUser(User user)
    {
        //function to push user to database

        return true;
    }
}
