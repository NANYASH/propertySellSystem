package com.dao.imp;

import com.dao.UserDAO;
import com.entity.User;
import com.exeption.BadRequestException;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Repository
public class UserDAOImp extends GenericDAO<User> implements UserDAO{

    private static final String SELECT_USER_BY_NAME = "SELECT * FROM USER2 WHERE USERNAME = ?";

    @Override
    public User findByUsername(String username) throws BadRequestException {
        Query query = getEntityManager().createNativeQuery(SELECT_USER_BY_NAME, User.class);
        query.setParameter(1, username);
        try {
            return (User) query.getSingleResult();
        }catch (NoResultException e){
            throw new BadRequestException("No users with such username");
        }
    }

}
