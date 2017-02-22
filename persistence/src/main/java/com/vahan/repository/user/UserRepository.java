package com.vahan.repository.user;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.model.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);



}
