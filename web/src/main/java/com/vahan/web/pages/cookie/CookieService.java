package com.vahan.web.pages.cookie;

/**
 * Created by vahan on 2/17/17.
 */

import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import javax.servlet.http.Cookie;


public interface CookieService {

    Cookie loadCookie(Request request, String cookieName);

    void saveCookie(Response response, String cookieName, String cookieValue, int expiryTimeInDays);

    void removeCookieIfPresent(Request request, Response response, String cookieName);

}
