package com.service;


import com.entity.Advert;
import com.entity.enums.PropertyClass;
import com.exeption.BadRequestExeption;

import java.util.List;

public interface FindMe {

    Advert addAdvert(String username,Advert advert) throws BadRequestExeption;
    Advert editAdvert(String username,Advert advert) throws BadRequestExeption;
    void deleteAdvert(String username,Long id) throws BadRequestExeption;

}
