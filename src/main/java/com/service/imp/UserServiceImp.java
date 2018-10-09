package com.service.imp;


import com.dao.UserDAO;
import com.entity.User;
import com.exeption.BadRequestExeption;
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
    public User registerUser(User user) throws BadRequestExeption {
        try {
            userDAO.findByUsername(user.getUsername());
        } catch (BadRequestExeption e) {
            user.setIsLoggedIn('N');
            return userDAO.save(user);
        }
        throw new BadRequestExeption("User \"" + user.getUsername() + "\" already exists");
    }

    @Override
    public User editUser(User user) throws BadRequestExeption {
        User userToUpdate = authenticate(user.getUsername());
        return userDAO.update(userToUpdate);
    }

    @Override
    public void logIn(String username, String password) throws BadRequestExeption {
        User userToLogIn = authenticate(username, password);
        userToLogIn.setIsLoggedIn('Y');
        userDAO.update(userToLogIn);
    }

    @Override
    public void logOut(String username) throws BadRequestExeption {
        User userToLogOut = authenticate(username);
        userToLogOut.setIsLoggedIn('N');
        userDAO.update(userToLogOut);
    }

    public User authenticate(String username) throws BadRequestExeption {
        User user = userDAO.findByUsername(username);
        if (user.getIsLoggedIn().equals('N'))
            throw new BadRequestExeption("User \"" + username + "\" is not logged in");
        return user;
    }

    private User authenticate(String username, String password) throws BadRequestExeption {
        User userToLogIn = userDAO.findByUsername(username);
        if (userToLogIn.getIsLoggedIn().equals('Y'))
            throw new BadRequestExeption("User \"" + username + "\" is already logged in");
        if (!userToLogIn.getPassword().equals(password))
            throw new BadRequestExeption("Login failed. Please check your credentials and try again.");
        return userToLogIn;
    }
}
