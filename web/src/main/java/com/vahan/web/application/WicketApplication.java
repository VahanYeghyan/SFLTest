package com.vahan.web.application;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.web.pages.*;
import com.vahan.web.pages.session.SecureWebSession;
import com.vahan.web.pages.session.SessionProvider;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;


public class WicketApplication extends AuthenticatedWebApplication {


    @Autowired
    private SessionProvider sessionProvider;

    @Override
    public Session newSession(Request request, Response response) {
        return sessionProvider.createNewSession(request);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();

        mountPage("home", HomePage.class);
        mountPage("login", LoginPage.class);
        mountPage("admin", ManagerPage.class);
        mountPage("accessDenied", AccessDeniedPage.class);
        mountPage("waiter", WaiterPage.class);


        getComponentInstantiationListeners().add(new SpringComponentInjector(this));


        getApplicationSettings().setAccessDeniedPage(MyCustomAccessDeniedPage.class);

        getSecuritySettings().setAuthorizationStrategy(new MetaDataRoleAuthorizationStrategy(this));
        MetaDataRoleAuthorizationStrategy.authorize(ManagerPage.class, Roles.ADMIN);
        MetaDataRoleAuthorizationStrategy.authorize(WaiterPage.class,Roles.USER);
        MetaDataRoleAuthorizationStrategy.authorize(WaiterPage.class,Roles.ADMIN);


    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return SecureWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }


}
