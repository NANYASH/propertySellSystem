package com.service;


import com.entity.Property;

public interface PropertyService {

    Property addProperty(Property property);
    Property editProperty(Property property);
    void deleteProperty(Long id);
}
