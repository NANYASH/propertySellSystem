package com.util;


import com.entity.enums.ApartmentClass;
import com.entity.enums.HouseFloors;
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
    private ApartmentClass apartmentClass;
    @Getter @Setter
    private HouseFloors houseFloors;
}
