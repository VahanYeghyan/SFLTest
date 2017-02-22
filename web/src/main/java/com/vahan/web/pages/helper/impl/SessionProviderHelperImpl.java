package com.vahan.web.pages.helper.impl;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.service.user.UserService;
import com.vahan.web.pages.helper.interfaces.SessionProviderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionProviderHelperImpl implements SessionProviderHelper {


    @Autowired
    UserService userService;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {

        return userService.findByUsernameAndPassword(username, password);
    }
}
