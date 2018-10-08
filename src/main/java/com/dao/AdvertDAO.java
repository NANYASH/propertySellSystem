package com.dao;

import com.entity.Advert;


public interface AdvertDAO {

    Advert save(Advert advert);

    Advert update(Advert advert);

    Advert findById(long id);

    void delete(Advert advert);
}
