package com.vahan.web.pages.cookie;

/**
 * Created by vahan on 2/17/17.
 */

import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CookieServiceImpl implements CookieService {


    public Cookie loadCookie(Request request, String cookieName) {

        List<Cookie> cookies = ((WebRequest) request).getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                System.out.println(cookie.getName());
                return cookie;
            }
        }

        return null;
    }

    public void saveCookie(Response response, String cookieName, String cookieValue, int expiryTimeInDays) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(expiryTimeInDays));
        ((WebResponse) response).addCookie(cookie);
    }

    public void removeCookieIfPresent(Request request, Response response, String cookieName) {
        Cookie cookie = loadCookie(request, cookieName);

        if (cookie != null) {
            ((WebResponse) response).clearCookie(cookie);
            System.out.println(cookie.getName());
        }
    }
}