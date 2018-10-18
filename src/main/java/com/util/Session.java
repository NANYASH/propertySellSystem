package com.util;


import com.entity.User;
import com.exeption.BadRequestException;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private static List<User> loggedInUsers;

    public static List<User> getLoggedInUsers() {
        if (loggedInUsers == null)
            loggedInUsers = new ArrayList<>();
        return loggedInUsers;
    }


    public static void logIn(User user,String password) throws BadRequestException {
        authenticate(user,password);
        loggedInUsers.add(user);

    }


    public static void logOut(User user) throws BadRequestException {
        authenticate(user);
        loggedInUsers.remove(user);
    }


    public static void authenticate(User user, String password) throws BadRequestException {
        if (getLoggedInUsers().contains(user))
            throw new BadRequestException("User \"" + user.getUsername() + "\" is already logged in");
        if (!user.getPassword().equals(password))
            throw new BadRequestException("Login failed. Please check your credentials and try again.");

    }
    
    public static void authenticate(User user) throws BadRequestException {
        if (!getLoggedInUsers().contains(user))
            throw new BadRequestException("User \"" + user.getUsername() + "\" is not logged in");
    }

}
