package com.controller;


import com.service.FindMe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdvertController {

    private FindMe findMe;

    @Autowired
    public AdvertController(FindMe findMe) {
        this.findMe = findMe;
    }
}
