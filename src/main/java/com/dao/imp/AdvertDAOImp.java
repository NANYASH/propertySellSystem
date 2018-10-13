package com.dao.imp;

import com.dao.AdvertDAO;
import com.entity.Advert;
import com.entity.Property;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.Filter;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Repository
public class AdvertDAOImp extends GenericDAO<Advert> implements AdvertDAO{

    @Override
    public Advert findById(long id) {
        return super.findById(Advert.class, id);
    }

    @Override
    public List<Advert> findByParameters(Filter filter) {
        return findAdvert(filter);
    }

    @Override
    public void delete(Advert advert) {
        getEntityManager().remove(getEntityManager().contains(advert) ? advert : getEntityManager().merge(advert));
    }

    //category
    //city
    //key words
    //date(not filter)
    private List<Advert> findAdvert(Filter filter) {
        Map<String, Object> filterParms = new ObjectMapper().convertValue(filter, Map.class);

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Advert> criteria = builder.createQuery(Advert.class);

        Root<Advert> root = criteria.from(Advert.class);
        Join<Advert, Property> join = root.join("property");

        Predicate predicate = builder.conjunction();

        Set<String> params = filterParms.entrySet().stream()
                .filter(param -> param.getValue() != null)
                .filter(param -> !param.getKey().equals("propertyClass"))
                .filter(param -> !param.getKey().equals("propertyType"))
                .map(param -> param.getKey())
                .collect(Collectors.toSet());

        for (String param : params) {
            if (param.equals("description")) {
                predicate = builder.and(predicate, builder.like(root.get(param), filterParms.get(param).toString()));
                continue;
            }
            predicate = builder.and(predicate, builder.equal(join.get(param), filterParms.get(param)));
        }

        if (filter.getPropertyType() != null)
            predicate = builder.and(predicate, builder.equal(join.get("propertyType"),filter.getPropertyType()));

        if (predicate != null)
            return getEntityManager().createQuery(criteria.select(root).where(predicate)).getResultList();

        return getEntityManager().createQuery(criteria.select(root)).getResultList();
    }
}
