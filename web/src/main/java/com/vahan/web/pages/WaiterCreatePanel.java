package com.vahan.web.pages;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.web.pages.helper.interfaces.ManagerHelper;
import com.vahan.web.pages.model.UserPageModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

//form in modal window to create waiter
class WaiterCreatePanel extends Panel {


    private static final long serialVersionUID = 3268882073201744772L;
    @SpringBean
    private ManagerHelper managerHelper;


    WaiterCreatePanel(String id, final WebMarkupContainer webMarkupContainer,final WebMarkupContainer webMarkupContainer1, final ModalWindow modalWindow, final UserPageModel userPageModel) {
        super(id);

        Form<Object> form = new Form<>("form");
        add(form);

        TextField<String> nameTextField = new TextField<>("name", new PropertyModel<String>(userPageModel, "name"));
        nameTextField.setRequired(true);
        form.add(nameTextField);

        TextField<String> usernameTextField = new TextField<>("username", new PropertyModel<String>(userPageModel, "username"));
        usernameTextField.setRequired(true);
        form.add(usernameTextField);

        TextField<String> passwordTextField = new TextField<>("password", new PropertyModel<String>(userPageModel, "password"));
        passwordTextField.setRequired(true);
        form.add(passwordTextField);

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);

        form.add(new AjaxButton("submitForm") {


            private static final long serialVersionUID = -6409984084534802926L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

                managerHelper.saveUser(userPageModel);

                managerHelper.changeListOfUsers(userPageModel);

                managerHelper.changeListOfWaiters(userPageModel);

                webMarkupContainer.modelChanged();

                webMarkupContainer1.modelChanged();

                target.add(webMarkupContainer);

                target.add(webMarkupContainer1);

                modalWindow.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
            }
        });
    }

}