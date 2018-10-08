package com.entity;


import com.entity.enums.PropertyClass;
import com.entity.enums.PropertyType;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY")
public class Property {
    private Long id;
    private Double area;
    private Integer roomsQuantity;
    private String region;
    private String city;
    private PropertyType propertyType;
    private PropertyClass propertyClass;

    @SequenceGenerator(name = "PROPERTY_SEQ", sequenceName = "PROPERTY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPERTY_SEQ")
    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "AREA")
    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Column(name = "ROOMS_QUANTITY")
    public Integer getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(Integer roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    @Column(name = "REGION")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "PROPERTY_TYPE")
    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "PROPERTY_CLASS")
    public PropertyClass getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(PropertyClass propertyClass) {
        this.propertyClass = propertyClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (id != null ? !id.equals(property.id) : property.id != null) return false;
        if (area != null ? !area.equals(property.area) : property.area != null) return false;
        if (roomsQuantity != null ? !roomsQuantity.equals(property.roomsQuantity) : property.roomsQuantity != null)
            return false;
        if (region != null ? !region.equals(property.region) : property.region != null) return false;
        if (city != null ? !city.equals(property.city) : property.city != null) return false;
        return propertyType == property.propertyType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (roomsQuantity != null ? roomsQuantity.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (propertyType != null ? propertyType.hashCode() : 0);
        return result;
    }
}
