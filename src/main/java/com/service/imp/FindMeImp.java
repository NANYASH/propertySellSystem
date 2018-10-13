package com.service.imp;


import com.dao.AdvertDAO;
import com.entity.Advert;
import com.entity.User;
import com.entity.enums.PropertyClass;
import com.exeption.BadRequestException;
import com.service.FindMe;
import com.service.PropertyService;
import com.service.UserService;
import com.util.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindMeImp implements FindMe {
    private AdvertDAO advertDAO;
    private UserService userService;

    @Autowired
    public FindMeImp(AdvertDAO advertDAO, UserService userService, PropertyService propertyService) {
        this.advertDAO = advertDAO;
        this.userService = userService;
    }


    @Override
    public Advert addAdvert(String username, Advert advert) throws BadRequestException {
        User author = userService.authenticate(username);
        advert.setAuthor(author);
        return advertDAO.save(advert);
    }

    @Override
    public Advert editAdvert(String username, Advert advert) throws BadRequestException {
        User author = userService.authenticate(username);
        validateAdvert(advert.getId(),author);
        return advertDAO.update(advert);
    }

    @Override
    public void deleteAdvert(String username, Long id) throws BadRequestException {
        User author = userService.authenticate(username);
        Advert advertToDelete = validateAdvert(id,author);
        advertDAO.delete(advertToDelete);

    }

    @Override
    public List<Advert> findAdvertsByParams(Filter filter) {
       List<Advert> foundAdverts = advertDAO.findByParameters(filter);
       if (filter.getPropertyClass()!=null&&filter.getPropertyType()!=null)
           return filterListByClass(foundAdverts,filter.getPropertyClass());
       return foundAdverts;
    }

    private List<Advert> filterListByClass(List<Advert> filteredByTypeList,PropertyClass propertyClass) {
        return filteredByTypeList.stream()
                 .filter(advert -> advert.getProperty().getPropertyClass().equals(propertyClass))
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
}
