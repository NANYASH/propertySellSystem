package com.controller;


import com.entity.User;
import com.exeption.BadRequestExeption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@Controller
public class UserController {
    private UserService userService;
    private ObjectMapper mapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/logOut", produces = "text/plain")
    @ResponseBody
    public void logOut(@RequestParam String username) throws BadRequestExeption {
        userService.logOut(username);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/logIn", produces = "text/plain")
    @ResponseBody
    public void logIn(@RequestParam String username,
                      @RequestParam String password) throws BadRequestExeption {
        userService.logIn(username,password);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registerUser", produces = "text/plain")
    @ResponseBody
    public String registerUser(HttpServletRequest req) throws BadRequestExeption {
       return userService.registerUser(mapToUser(req)).toString();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/editUser", produces = "text/plain")
    @ResponseBody
    public User editUser(HttpServletRequest req) throws BadRequestExeption {
        return userService.editUser(mapToUser(req));
    }

    private User mapToUser(HttpServletRequest req) throws BadRequestExeption {
        try {
            return mapper.readValue(
                    mapper.writeValueAsString(mapper.readTree(req.getInputStream()).path("user")),
                    new TypeReference<User>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestExeption("Data cannot be mapped to User type");
        }
    }
}
