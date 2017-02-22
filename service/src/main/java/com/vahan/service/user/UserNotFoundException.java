package com.vahan.service.user;

/**
 * Created by vahan on 2/17/17.
 */

import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserNotFoundException extends UsernameNotFoundException {
    private static final long serialVersionUID = -1609522734428933368L;

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
