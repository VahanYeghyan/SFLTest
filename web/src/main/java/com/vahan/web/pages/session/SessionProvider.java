package com.vahan.web.pages.session;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.web.pages.cookie.CookieService;
import com.vahan.web.pages.helper.interfaces.SessionProviderHelper;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;


@Component
public class SessionProvider {

    public static final int REMEMBER_ME_DURATION_IN_DAYS = 30;
    public static final String REMEMBER_ME_LOGIN_COOKIE = "loginCookie";
    public static final String REMEMBER_ME_PASSWORD_COOKIE = "passwordCookie";


    /*@Autowired
    private SessionProviderHelper sessionProviderHelper;*/

    @Autowired
    private  CookieService cookieService;

    @Autowired
    private  SessionProviderHelper sessionProviderHelper;




    public WebSession createNewSession(Request request) {

        SecureWebSession secureWebSession = new SecureWebSession(request);

        Cookie loginCookie = cookieService.loadCookie(request, REMEMBER_ME_LOGIN_COOKIE);
        Cookie passwordCookie = cookieService.loadCookie(request, REMEMBER_ME_PASSWORD_COOKIE);

        if (loginCookie != null && passwordCookie != null) {


            User user = sessionProviderHelper.getUserByUsernameAndPassword(loginCookie.getValue(), passwordCookie.getValue());

            if (user != null) {
                secureWebSession.setUser(user);
            }
        }
        return secureWebSession;

    }

}
