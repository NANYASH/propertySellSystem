package com.service.imp;


import com.dao.UserDAO;
import com.entity.User;
import com.exeption.BadRequestException;
import com.service.UserService;
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
            user.setIsLoggedIn('N');
            return userDAO.save(user);
        }
        throw new BadRequestException("User \"" + user.getUsername() + "\" already exists");
    }

    @Override
    public User editUser(User user) throws BadRequestException {
        User userToUpdate = authenticate(user.getUsername());
        return userDAO.update(userToUpdate);
    }

    @Override
    public void logIn(String username, String password) throws BadRequestException {
        User userToLogIn = authenticate(username, password);
        userToLogIn.setIsLoggedIn('Y');
        userDAO.update(userToLogIn);
    }

    @Override
    public void logOut(String username) throws BadRequestException {
        User userToLogOut = authenticate(username);
        userToLogOut.setIsLoggedIn('N');
        userDAO.update(userToLogOut);
    }

    public User authenticate(String username) throws BadRequestException {
        User user = userDAO.findByUsername(username);
        if (user.getIsLoggedIn().equals('N'))
            throw new BadRequestException("User \"" + username + "\" is not logged in");
        return user;
    }

    private User authenticate(String username, String password) throws BadRequestException {
        User userToLogIn = userDAO.findByUsername(username);
        if (userToLogIn.getIsLoggedIn().equals('Y'))
            throw new BadRequestException("User \"" + username + "\" is already logged in");
        if (!userToLogIn.getPassword().equals(password))
            throw new BadRequestException("Login failed. Please check your credentials and try again.");
        return userToLogIn;
    }
}
