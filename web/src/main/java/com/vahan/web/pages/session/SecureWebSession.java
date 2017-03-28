package com.vahan.web.pages.session;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.model.user.UserType;
import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SecureWebSession extends AuthenticatedWebSession {


    private static final long serialVersionUID = -688116078226608771L;

    private static Logger logger = Logger.getLogger(SecureWebSession.class);

    private HttpSession httpSession;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean userLogedIn() {
        return user != null;
    }

    public boolean userNotLogedIn() {
        return user == null;
    }

    @SpringBean(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    SecureWebSession(Request request) {
        super(request);
        this.httpSession = ((HttpServletRequest) request.getContainerRequest()).getSession();
        Injector.get().inject(this);
    }

    public static SecureWebSession getHttpSession() {
        return (SecureWebSession) AuthenticatedWebSession.get();
    }

    @Override
    public boolean authenticate(String username, String password) {
        try {
            final Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            if (auth.isAuthenticated()) {

                SecurityContextHolder.getContext().setAuthentication(auth);
                httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                return true;
            } else {
                return false;
            }
        } catch (AuthenticationException e) {
            logger.warn("Failed login!", e);
            return false;
        }
    }

    @Override
    public Roles getRoles() {


        Roles roles = new Roles();
        if (getUser() != null) {

            if (getUser().getUserType() == UserType.MANAGER) {
                roles.add(Roles.ADMIN);
            }
            else if (getUser().getUserType() == UserType.WAITER){
                roles.add(Roles.USER);
            }
        }

        return roles;
    }


}
