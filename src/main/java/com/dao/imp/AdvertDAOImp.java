package com.dao.imp;

import com.dao.AdvertDAO;
import com.entity.Advert;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AdvertDAOImp extends GenericDAO<Advert> implements AdvertDAO{


    @Override
    public Advert findById(long id) {
        return super.findById(Advert.class,id);
    }

    @Transactional
    @Override
    public void delete(Advert advert) {
       getEntityManager().detach(advert);
    }
}
