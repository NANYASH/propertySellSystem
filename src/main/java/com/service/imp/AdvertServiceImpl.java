package com.service.imp;


import com.dao.AdvertDAO;
import com.entity.Advert;
import com.entity.User;
import com.entity.enums.ApartmentClass;
import com.entity.enums.HouseFloors;
import com.entity.enums.PropertyType;
import com.exeption.BadRequestException;
import com.service.AdvertService;
import com.service.PropertyService;
import com.service.UserService;
import com.util.Filter;
import com.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertServiceImpl implements AdvertService {
    private AdvertDAO advertDAO;
    private UserService userService;

    @Autowired
    public AdvertServiceImpl(AdvertDAO advertDAO, UserService userService, PropertyService propertyService) {
        this.advertDAO = advertDAO;
        this.userService = userService;
    }


    @Override
    public Advert addAdvert(String username, Advert advert) throws BadRequestException {
        User author = userService.findUserByUsername(username);
        Session.validateLogIn(author);
        validateAdvert(advert);
        advert.setAuthor(author);
        advert.setCreatedDate(new Date());
        advert.setLastUpdateDate(advert.getCreatedDate());
        return advertDAO.save(advert);
    }

    @Override
    public Advert editAdvert(String username, Advert advert) throws BadRequestException {
        User author = userService.findUserByUsername(username);
        Session.validateLogIn(author);
        validateAdvert(advert.getId(),author);
        validateAdvert(advert);
        advert.setLastUpdateDate(new Date());
        return advertDAO.update(advert);
    }

    @Override
    public void deleteAdvert(String username, Long id) throws BadRequestException {
        User author = userService.findUserByUsername(username);
        Session.validateLogIn(author);
        Advert advertToDelete = validateAdvert(id,author);
        advertDAO.delete(advertToDelete);

    }

    @Override
    public List<Advert> findAdvertsByParams(Filter filter) {
       List<Advert> foundAdverts = advertDAO.findByParams(filter);

       if (filter.getPropertyType() == PropertyType.APARTMENT && filter.getApartmentClass()!=null)
           return filterListByApartClass(foundAdverts,filter.getApartmentClass());

       if (filter.getPropertyType() == PropertyType.HOUSE && filter.getHouseFloors()!=null)
            return filterListByHouseFloors(foundAdverts,filter.getHouseFloors());

       return foundAdverts;
    }

    private List<Advert> filterListByApartClass(List<Advert> filteredByTypeList, ApartmentClass apartmentClass) {
        return filteredByTypeList.stream()
                .filter(advert -> advert.getProperty().getApartmentClass().equals(apartmentClass))
                .collect(Collectors.toList());
    }

    private List<Advert> filterListByHouseFloors(List<Advert> filteredByTypeList, HouseFloors houseFloors) {
        return filteredByTypeList.stream()
                .filter(advert -> advert.getProperty().getHouseFloors().equals(houseFloors))
                .collect(Collectors.toList());
    }

    private Advert validateAdvert(Long id,User user) throws BadRequestException {
        Advert advertToUpdate = advertDAO.findById(id);
        if (advertToUpdate == null)
            throw new BadRequestException("Advert does not exist");
        if (!advertToUpdate.getAuthor().equals(user))
            throw new BadRequestException("User \"" + user.getUsername() + "\" is not an author of such advert");
        return advertToUpdate;
    }

    private void validateAdvert(Advert advert) throws BadRequestException {
        if (advert.getAvailableToDate().before(advert.getAvailableFromDate()))
            throw new BadRequestException("Incorrect available date are entered");
    }
}
