package com.example.e_comerce.JavaClasses;

public class CustomerAuthentication implements IUserAuthentication {

    boolean isAuthenticated=false;
    public User authenticate(String username, String password)  {


        //checkCustomerExists(username,password);


        // User GetCustomerUser()


        Date customerDateOfBirth = new Date(15, 5, 1990);


        Customer customer = new Customer("Customer mahmoud", "123", customerDateOfBirth);
        return customer;
    }
}
