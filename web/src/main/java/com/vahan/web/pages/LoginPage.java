package com.vahan.web.pages;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.web.pages.cookie.CookieService;
import com.vahan.web.pages.helper.interfaces.LoginPageHelper;
import com.vahan.web.pages.session.SecureWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import static com.vahan.web.pages.session.SessionProvider.*;


public class LoginPage extends WebPage {


    private static final long serialVersionUID = 6998149831884372775L;
    @SpringBean
    private CookieService cookieService;

    @SpringBean
    private LoginPageHelper loginPageHelper;


    public LoginPage() {
        add(new LoginForm("loginForm"));
    }


    //form for logging in
    private class LoginForm extends Form<Void> {


        private static final long serialVersionUID = -6930244739264778212L;
        private String username;

        private String password;

        private boolean rememberMe;


        LoginForm(String id) {
            super(id);
            setModel(new CompoundPropertyModel(this));
            add(new RequiredTextField<String>("username"));
            add(new PasswordTextField("password"));
            add(new CheckBox("rememberMe"));
            add(new FeedbackPanel("feedback"));
        }

        @Override
        protected void onSubmit() {

            AuthenticatedWebSession session = AuthenticatedWebSession.get();
            if (session.signIn(username, password)) {

                User user = loginPageHelper.getUserByUsernameAndPassword(username, password);

                if (user != null) {
                    SecureWebSession.getHttpSession().setUser(user);

                    //checking for remember me
                    if (rememberMe) {
                        cookieService.saveCookie(getResponse(), REMEMBER_ME_LOGIN_COOKIE, user.getUsername(), REMEMBER_ME_DURATION_IN_DAYS);
                        cookieService.saveCookie(getResponse(), REMEMBER_ME_PASSWORD_COOKIE, user.getPassword(), REMEMBER_ME_DURATION_IN_DAYS);
                    }
                    setResponsePage(HomePage.class);

                }
            } else {
                error("Login failed due to invalid credentials");
            }
        }
    }
}
