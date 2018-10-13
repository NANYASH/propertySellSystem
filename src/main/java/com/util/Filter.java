package com.util;


import com.entity.enums.PropertyClass;
import com.entity.enums.PropertyType;
import lombok.Getter;
import lombok.Setter;

public class Filter {
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private PropertyType propertyType;
    @Getter @Setter
    private PropertyClass propertyClass;
}
