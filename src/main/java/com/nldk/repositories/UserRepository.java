package com.nldk.repositories;


import com.nldk.pojo.User;
import com.nldk.repositories.irepositories.IUserRepositories;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UserRepository implements IUserRepositories {
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    public void createUser(User user) {
         Session session = sessionFactory.getObject().getCurrentSession();
         session.save(user);
    }
    
}
