package com.util;


import com.entity.enums.PropertyClass;
import com.entity.enums.PropertyType;

public class Filter {
    private String city;
    private String description;
    private PropertyType propertyType;
    private PropertyClass propertyClass;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyClass getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(PropertyClass propertyClass) {
        this.propertyClass = propertyClass;
    }
}
