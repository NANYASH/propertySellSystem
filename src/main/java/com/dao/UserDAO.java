package com.dao;


import com.entity.User;
import com.exeption.BadRequestExeption;

public interface UserDAO {

    User save(User user);

    User findByUsername(String username) throws BadRequestExeption;

    User update(User user);


}
