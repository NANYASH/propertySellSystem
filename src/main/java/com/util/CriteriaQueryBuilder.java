package com.util;


import com.dao.imp.GenericDAO;
import com.entity.Advert;
import com.entity.Property;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CriteriaQueryBuilder extends GenericDAO {
    private Filter filter;
    CriteriaBuilder builder;
    CriteriaQuery<Advert> criteria;
    Root<Advert> root;
    CriteriaQuery<Advert> select;
    Join<Advert,Property> join;
    List<Predicate> predicate;

    public void init(Filter filter) {
        this.filter = filter;
        this.builder = getEntityManager().getCriteriaBuilder();
        this.criteria = builder.createQuery(Advert.class);
        this.root = criteria.from(Advert.class);
        this.select = this.criteria.select(root);
        this.predicate = new ArrayList<>();
    }

    private CriteriaQueryBuilder createFilterByCity(){
        if (filter.getCity()!=null) {
            join = root.join("property");
            predicate.add(this.builder.equal(root.get("city"), filter.getCity()));
        }
        return this;
    }

   /* private CriteriaQueryBuilder createDescription(){
        if (filter.getDescription()!=null)
            predicate.add(this.builder.cont(root.get("cityFrom"),filter.getCityFrom()));
        return this;
    }

    private CriteriaQueryBuilder createDateFlight(){
        if (filter.getDateFlight()!=null)
            predicate.add(this.builder.equal(root.get("dateFlight"),filter.getDateFlight()));
        return this;
    }



    public void buildPredicates(){
        createModel();
        createFilterByCity();
        createFilterCityFrom();
    }

    public CriteriaQuery getFilterQuery(Filter filter){
        init(filter);
        buildPredicates();
        return select.where(builder.or(predicate.toArray(new Predicate[0])));
    }*/
}
