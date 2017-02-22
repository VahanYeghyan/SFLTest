package com.vahan.web.pages.helper.interfaces;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;


public interface LoginPageHelper {

    User getUserByUsernameAndPassword(String username,String password);
}
