package com.vahan.web.pages;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.user.User;
import com.vahan.web.pages.helper.interfaces.ManagerHelper;
import com.vahan.web.pages.model.OrderPageModel;
import com.vahan.web.pages.model.ProductPageModel;
import com.vahan.web.pages.model.TablePageModel;
import com.vahan.web.pages.model.UserPageModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;


public class ManagerPage extends WebPage implements Serializable {

    private static final long serialVersionUID = -6916680589041670502L;
    @SpringBean
    private ManagerHelper managerHelper;

    private UserPageModel userPageModel;

    private OrderPageModel orderPageModel;

    private ModalWindow userModalWindow;

    private ModalWindow orderModalWindow;

    private TablePageModel tablePageModel;

    private ProductPageModel productPageModel;

    public ManagerPage() {

        userPageModel = new UserPageModel();

        orderPageModel = new OrderPageModel();

        tablePageModel = new TablePageModel();

        productPageModel = new ProductPageModel();

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        initialize();

    }

    private void initialize() {

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        UsersDataViewContainer usersDataViewContainer = new UsersDataViewContainer("userDataViewMarkup", new Model<>());
        usersDataViewContainer.setOutputMarkupId(true);
        add(usersDataViewContainer);

        WaiterToTableContainer waiterToTableContainer = new WaiterToTableContainer("waiterToTableMarkup", new Model<>());
        waiterToTableContainer.setOutputMarkupId(true);
        add(waiterToTableContainer);

        TextField<Integer> tableNumberTextField = new TextField<>("tableNumber", new PropertyModel<>(tablePageModel, "number"));
        tableNumberTextField.setType(Integer.class);

        Link toHomePageLink = new Link("toHomePage") {
            private static final long serialVersionUID = 4579251015247592338L;

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        };

        Link toWaiterPageLink = new Link("toWaiterPage") {
            private static final long serialVersionUID = -8640641274385757189L;

            @Override
            public void onClick() {
                setResponsePage(WaiterPage.class);
            }
        };

        add(toHomePageLink);
        add(toWaiterPageLink);


        Form<Void> tableForm = new Form<Void>("tableForm");


        tableForm.add(new AjaxButton("tableSubmit") {

            private static final long serialVersionUID = 4395617752883731213L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

                super.onSubmit(target, form);

                managerHelper.createTable(tablePageModel);

                managerHelper.changeTableNumberList(tablePageModel);

                waiterToTableContainer.modelChanged();

                target.add(waiterToTableContainer);

                info("table was created");

                target.add(feedbackPanel);

            }


        });
        add(tableForm);
        tableForm.add(tableNumberTextField);


        WebMarkupContainer userMarkupContainer = new WebMarkupContainer("userMarkup");

        add(new AjaxLink<Object>("clickToOpenUserForm") {

            private static final long serialVersionUID = -6224239684280346596L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                userModalWindow.show(target);
            }
        });

        userModalWindow = new ModalWindow("userModal");

        WaiterCreatePanel waiterCreatePanel = new WaiterCreatePanel("content", usersDataViewContainer, waiterToTableContainer, userModalWindow, userPageModel);

        userModalWindow.setContent(waiterCreatePanel);

        userMarkupContainer.add(userModalWindow);

        add(userMarkupContainer);


        TextField<String> productNameTextField = new TextField<>("productName", new PropertyModel<>(productPageModel, "name"));
        productNameTextField.setRequired(true);

        TextField<Integer> productPriceTextField = new TextField<Integer>("productPriceTextField", new PropertyModel<>(productPageModel, "price"));
        productPriceTextField.setRequired(true);

        Form<Void> productForm = new Form<Void>("productForm") {
            @Override
            protected void onSubmit() {
                managerHelper.createProduct(productPageModel);
                info("product created");
            }
        };
        add(productForm);
        productForm.add(productNameTextField);
        productForm.add(productPriceTextField);


    }

    private class WaiterToTableContainer extends WebMarkupContainer {

        private static final long serialVersionUID = -8856562144904888365L;

        WaiterToTableContainer(String id, IModel<?> model) {
            super(id, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            setOutputMarkupId(true);

            ChoiceRenderer<User> userChoiceRenderer = new ChoiceRenderer<>("username");

            DropDownChoice<User> userDropDownChoice = new DropDownChoice<User>("selectWaiter", new PropertyModel<>(tablePageModel, "user"), managerHelper.getAllUsers(userPageModel), userChoiceRenderer) {

                private static final long serialVersionUID = -4939323276083735477L;
            };


            DropDownChoice<Integer> tableDropDownChoice = new DropDownChoice<Integer>("selectTable", new PropertyModel<>(tablePageModel, "number"), managerHelper.tableNumbersList(tablePageModel)) {

                private static final long serialVersionUID = -7716875990877334803L;
            };

            Form<Void> waitersToTables = new Form<Void>("waitersToTables") {
                private static final long serialVersionUID = -4994263874581451967L;

                @Override
                protected void onSubmit() {

                    if (tablePageModel.getUser().getUsername().equals("poxos1")) {
                        info("you can't attach manager to table");

                    } else {
                        managerHelper.setWaiterToTable(tablePageModel);

                        info("waiter was attached to table");
                    }
                }
            };
            userDropDownChoice.setRequired(true);
            tableDropDownChoice.setRequired(true);
            add(waitersToTables);
            waitersToTables.add(userDropDownChoice);
            waitersToTables.add(tableDropDownChoice);
        }
    }

    private class UsersDataViewContainer extends WebMarkupContainer {

        UsersDataViewContainer(String id, IModel<?> model) {
            super(id, model);
        }

        @Override
        protected void onInitialize() {

            super.onInitialize();

            final DataView dataView = new UserDataView("userDataView", new ListDataProvider<>(managerHelper.getAllUsers(userPageModel)));

            add(new PagingNavigator("navigator", dataView));

            dataView.setItemsPerPage(25);

            add(dataView);
        }

        private class UserDataView extends DataView<User> {

            private UserDataView(String id, IDataProvider<User> dataProvider) {
                super(id, dataProvider);
            }

            @Override
            protected void populateItem(Item<User> item) {
                final User user = item.getModelObject();

                item.add(new Label("name", user.getName()));
                item.add(new Label("username", user.getUsername()));
                item.add(new Label("userType", user.getUserType()));
            }
        }
    }


}
