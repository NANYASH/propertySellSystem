package com.service.imp;


import com.dao.PropertyDAO;
import com.entity.Property;
import com.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImp implements PropertyService{

    private PropertyDAO propertyDAO;

    @Autowired
    public PropertyServiceImp(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    @Override
    public Property addProperty(Property property){
        return propertyDAO.save(property);
    }

    @Override
    public Property editProperty(Property property) {
        return propertyDAO.update(property);
    }

    @Override
    public void deleteProperty(Long id) {
        propertyDAO.delete(id);
    }
}
