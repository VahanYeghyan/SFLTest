package com.vahan.service.user;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.model.user.UserType;

import java.util.List;


public interface UserService {

    void saveUser(User user);

    User findByUsernameAndPassword(String username, String password);

    List<User> usersList();

    User getUserByName(String username);

    List<User> getOnlyWaiters();

}
