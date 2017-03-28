package com.vahan.web.pages;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.web.pages.cookie.CookieService;
import com.vahan.web.pages.cookie.CookieServiceImpl;
import com.vahan.web.pages.helper.interfaces.HomePageHelper;
import com.vahan.web.pages.session.SecureWebSession;
import com.vahan.web.pages.session.SessionProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class HomePage extends WebPage {

    private static final long serialVersionUID = 1000207076965701829L;
    @SpringBean
    private HomePageHelper homePageHelper;

    @SpringBean
    private CookieService cookieService;


    public HomePage(PageParameters parameters) {
        super(parameters);
        add(new Label("welcome", "Welcome to home page"));

        //link to logout
        Link<Void> logout = new Link<Void>("logoutLink") {

            private static final long serialVersionUID = -6982829694975330867L;

            @Override
            public void onClick() {
                cookieService.removeCookieIfPresent(getRequest(), getResponse(), SessionProvider.REMEMBER_ME_LOGIN_COOKIE);
                cookieService.removeCookieIfPresent(getRequest(), getResponse(), SessionProvider.REMEMBER_ME_PASSWORD_COOKIE);

                System.out.println(SecureWebSession.getHttpSession().getUser());

                SecureWebSession.getHttpSession().setUser(null);
                SecureWebSession.getHttpSession().invalidate();
                setResponsePage(LoginPage.class);

            }
        };
        add(logout);

        //link to login
        Link<Void> loginLink = new Link<Void>("login") {
            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        };
        add(loginLink);

        //link to manager page
        Link<Void> managerLink = new Link<Void>("admin") {
            @Override
            public void onClick() {
                try {

                    //checking for cookies

                    CookieServiceImpl cookieServiceImpl = new CookieServiceImpl();

                    javax.servlet.http.Cookie loginCookie = cookieServiceImpl.loadCookie(getRequest(), SessionProvider.REMEMBER_ME_LOGIN_COOKIE);

                    javax.servlet.http.Cookie passwordCookie = cookieServiceImpl.loadCookie(getRequest(), SessionProvider.REMEMBER_ME_PASSWORD_COOKIE);

                    if (loginCookie != null && passwordCookie != null) {
                        User user = homePageHelper.getUserByUsernameAndPassword(loginCookie.getValue(), passwordCookie.getValue());
                        if (user != null) {
                            setResponsePage(ManagerPage.class);
                            return;
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                    continueToOriginalDestination();
                }

                if (SecureWebSession.getHttpSession().getUser() != null) {
                    setResponsePage(ManagerPage.class);

                } else {
                    setResponsePage(LoginPage.class);
                }
            }
        };
        add(managerLink);

        //link to user page
        Link<Void> waiterPageLink = new Link<Void>("userPage") {
            private static final long serialVersionUID = -4437254636877662946L;

            @Override
            public void onClick() {
                try {

                    //checking for cookies

                    CookieServiceImpl cookieServiceImpl = new CookieServiceImpl();

                    javax.servlet.http.Cookie loginCookie = cookieServiceImpl.loadCookie(getRequest(), SessionProvider.REMEMBER_ME_LOGIN_COOKIE);

                    javax.servlet.http.Cookie passwordCookie = cookieServiceImpl.loadCookie(getRequest(), SessionProvider.REMEMBER_ME_PASSWORD_COOKIE);

                    if (loginCookie != null && passwordCookie != null) {
                        User user = homePageHelper.getUserByUsernameAndPassword(loginCookie.getValue(), passwordCookie.getValue());
                        if (user != null) {
                            setResponsePage(WaiterPage.class);
                            return;
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                    continueToOriginalDestination();
                }

                if (SecureWebSession.getHttpSession().getUser() != null) {
                    setResponsePage(WaiterPage.class);

                } else {
                    setResponsePage(LoginPage.class);
                }
            }
        };

        add(waiterPageLink);

        if (SecureWebSession.getHttpSession().getUser() == null){
            logout.setVisible(false);
        }
        else{
            loginLink.setVisible(false);
        }
    }
}
