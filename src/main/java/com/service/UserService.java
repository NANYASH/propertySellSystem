package com.service;


import com.entity.User;
import com.exeption.BadRequestExeption;


public interface UserService {

    User registerUser(User user) throws BadRequestExeption;
    User editUser(User user) throws BadRequestExeption;
    void logIn(String username,String password) throws BadRequestExeption;
    void logOut(String username) throws BadRequestExeption;
    User authenticate(String username) throws BadRequestExeption;
}
