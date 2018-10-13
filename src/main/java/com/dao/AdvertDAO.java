package com.dao;

import com.entity.Advert;
import com.util.Filter;

import java.util.List;


public interface AdvertDAO {

    Advert save(Advert advert);

    Advert update(Advert advert);

    Advert findById(long id);

    List<Advert> findByParameters(Filter filter);

    void delete(Advert advert);
}
