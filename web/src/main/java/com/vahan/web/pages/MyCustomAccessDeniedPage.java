package com.vahan.web.pages;

/**
 * Created by vahan on 2/17/17.
 */

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

//custom access denied page
public class MyCustomAccessDeniedPage extends WebPage {
    public MyCustomAccessDeniedPage() {
        add(new Link<Void>("homePage") {
            private static final long serialVersionUID = -177361528070812913L;

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });
    }
}
