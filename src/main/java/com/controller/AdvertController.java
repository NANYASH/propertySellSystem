package com.controller;


import com.entity.Advert;
import com.entity.enums.PropertyClass;
import com.entity.enums.PropertyType;
import com.exeption.BadRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.FindMe;
import com.util.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class AdvertController {
    private FindMe findMe;
    private ObjectMapper mapper;

    @Autowired
    public AdvertController(FindMe findMe) {
        this.findMe = findMe;
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByParams", produces = "text/plain")
    @ResponseBody
    public String findAdvertsByParams(@RequestParam(required = false) String city,
                                      @RequestParam(required = false) String description,
                                      @RequestParam(required = false) String propertyType,
                                      @RequestParam(required = false) String propertyClass) throws BadRequestException {

        Filter filter = new Filter();
        filter.setCity(city);
        filter.setDescription(description);
        try {
            if (propertyType!=null)
            filter.setPropertyType(PropertyType.valueOf(propertyType.toUpperCase().trim()));
            if (propertyClass!=null)
            filter.setPropertyClass(PropertyClass.valueOf(propertyClass.toUpperCase().trim()));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new BadRequestException("Params(Param Class or/and ParamType) is/are  supposed to be in incorrect type");
        }
        return findMe.findAdvertsByParams(filter).toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addAdvert", produces = "text/plain")
    @ResponseBody
    public String addAdvert(@RequestParam String username, HttpServletRequest req) throws BadRequestException {
        return findMe.addAdvert(username, mapToAdvert(req)).toString();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/editAdvert", produces = "text/plain")
    @ResponseBody
    public String editAdvert(@RequestParam String username, HttpServletRequest req) throws BadRequestException {
        return findMe.editAdvert(username, mapToAdvert(req)).toString();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteAdvert", produces = "text/plain")
    @ResponseBody
    public void deleteAdvert(@RequestParam String username, @RequestParam long id) throws BadRequestException {
        findMe.deleteAdvert(username, id);
    }

    private Advert mapToAdvert(HttpServletRequest req) throws BadRequestException {
        try {
            return mapper.readValue(
                    mapper.writeValueAsString(mapper.readTree(req.getInputStream()).path("advert")),
                    new TypeReference<Advert>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException("Data cannot be mapped to Advert type");
        }
    }
}
