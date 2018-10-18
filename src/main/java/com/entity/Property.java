package com.entity;


import com.entity.enums.ApartmentClass;
import com.entity.enums.HouseFloors;
import com.entity.enums.PropertyType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY")
@EqualsAndHashCode
@ToString
public class Property {

    @SequenceGenerator(name = "PROPERTY_SEQ", sequenceName = "PROPERTY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPERTY_SEQ")
    @Id
    @Column(name = "ID")
    @Getter @Setter
    private Long id;

    @Column(name = "AREA")
    @Getter @Setter
    private Double area;

    @Column(name = "ROOMS_QUANTITY")
    @Getter @Setter
    private Integer roomsQuantity;

    @Column(name = "REGION")
    @Getter @Setter
    private String region;

    @Column(name = "CITY")
    @Getter @Setter
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROPERTY_TYPE")
    @Getter @Setter
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "APART_CLASS  ")
    @Getter @Setter
    private ApartmentClass apartmentClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "HOUSE_FLOORS")
    @Getter @Setter
    private HouseFloors houseFloors;
}
