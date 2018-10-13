package com.dao;


import com.entity.User;
import com.exeption.BadRequestException;

public interface UserDAO {

    User save(User user);

    User findByUsername(String username) throws BadRequestException;

    User update(User user);


}
