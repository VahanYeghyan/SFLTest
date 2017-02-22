package com.vahan.web.pages.helper.impl;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.service.user.UserService;
import com.vahan.web.pages.helper.interfaces.HomePageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePageHelperImpl implements HomePageHelper {

    private final UserService userService;

    @Autowired
    public HomePageHelperImpl(UserService userService) {
        this.userService = userService;
    }

    //getting user by username and password
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userService.findByUsernameAndPassword(username, password);
    }
}
