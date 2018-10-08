package com.dao;

import com.entity.Property;

public interface PropertyDAO {

    Property save(Property property);

    Property update(Property property);

    void delete(Long id);
}
