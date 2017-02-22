package com.vahan.service.user;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component(value = "userServiceDetails")
public class UserServiceDetails implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    public UserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //loading user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        com.vahan.model.user.User user = userService.getUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user was found for %s name", username));
        }


        final Set<GrantedAuthority> authorities = new HashSet<>();
        final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(grantedAuthority);


        return new User(user.getUsername(), user.getPassword(), authorities);

    }
}
