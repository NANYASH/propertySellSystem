package com.service;


import com.entity.Advert;
import com.exeption.BadRequestException;
import com.util.Filter;

import java.util.List;

public interface AdvertService {

    Advert addAdvert(String username,Advert advert) throws BadRequestException;
    Advert editAdvert(String username,Advert advert) throws BadRequestException;
    void deleteAdvert(String username,Long id) throws BadRequestException;
    List<Advert> findAdvertsByParams(Filter filter);

}
