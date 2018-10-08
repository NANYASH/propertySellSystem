package com.dao.imp;


import com.dao.PropertyDAO;
import com.entity.Property;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class PropertyDAOImp extends GenericDAO<Property> implements PropertyDAO{
    @Transactional
    @Override
    public void delete(Long id) {
        super.delete(Property.class,id);
    }
}
