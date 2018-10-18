package com.service;


import com.entity.User;
import com.exeption.BadRequestException;


public interface UserService {

    User registerUser(User user) throws BadRequestException;
    User editUser(User user) throws BadRequestException;
    void logIn(String username,String password) throws BadRequestException;
    void logOut(String username) throws BadRequestException;
    User findUserByUsername(String username) throws BadRequestException;
}
