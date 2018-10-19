package com.service.imp;


import com.dao.UserDAO;
import com.entity.User;
import com.exeption.BadRequestException;
import com.service.UserService;
import com.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User registerUser(User user) throws BadRequestException {
        try {
            userDAO.findByUsername(user.getUsername());
        } catch (BadRequestException e) {
            return userDAO.save(user);
        }
        throw new BadRequestException("User \"" + user.getUsername() + "\" already exists");
    }

    @Override
    public User editUser(User user) throws BadRequestException {
        User userToUpdate = userDAO.findByUsername(user.getUsername());
        Session.validateLogIn(user);
        return userDAO.update(userToUpdate);
    }

    @Override
    public void logIn(String username, String password) throws BadRequestException {
        User userToLogIn = userDAO.findByUsername(username);
        Session.logIn(userToLogIn,password);
    }

    @Override
    public void logOut(String username) throws BadRequestException {
        User userToLogOut = userDAO.findByUsername(username);
        Session.logOut(userToLogOut);
    }

    @Override
    public User findUserByUsername(String username) throws BadRequestException {
        return userDAO.findByUsername(username);
    }

}
