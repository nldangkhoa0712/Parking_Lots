package com.nldk.repositories;


import com.nldk.pojo.User;
import com.nldk.repositories.irepositories.IUserRepositories;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
//@Transactional
public class UserRepository implements IUserRepositories {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        try {
            org.hibernate.query.Query<User> query = session.createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from User where email = :email and passwordHash = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return User.class.cast(query.getSingleResult());
    }
}
