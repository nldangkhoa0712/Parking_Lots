package com.nldk.repositories;


import com.nldk.pojo.User;
import com.nldk.repositories.irepositories.IUserRepositories;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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
//    @Transactional
    public User getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return User.class.cast(query.getSingleResult());
    }
}
