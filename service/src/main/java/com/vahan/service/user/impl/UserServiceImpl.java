package com.vahan.service.user.impl;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.model.user.UserType;
import com.vahan.repository.user.UserRepository;
import com.vahan.service.user.UserNotFoundException;
import com.vahan.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager em;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //saving user
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveUser(User user)throws UsernameNotFoundException {

        if (user == null) {
            throw new UserNotFoundException("user must be not null");
        }
        userRepository.save(user);
    }

    //finding user by username and password
    @Transactional
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    //getting whole user list
    @Transactional
    @Override
    public List<User> usersList() {
        return userRepository.findAll();
    }


    //getting user by username
    @Transactional
    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getOnlyWaiters() {

        String query = "select * from users where userType = 'WAITER'";

        Query query1 = em.createNativeQuery(query);

        return (List<User>) query1.getResultList();
    }
}
