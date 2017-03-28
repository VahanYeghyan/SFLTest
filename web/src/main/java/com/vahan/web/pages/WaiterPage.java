package com.vahan.web.pages;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.order.Order;
import com.vahan.model.product.Product;
import com.vahan.model.user.UserType;
import com.vahan.web.pages.helper.interfaces.ManagerHelper;
import com.vahan.web.pages.helper.interfaces.WaiterPageHelper;
import com.vahan.web.pages.model.OrderPageModel;
import com.vahan.web.pages.model.ProductInOrderPageModel;
import com.vahan.web.pages.model.ProductPageModel;
import com.vahan.web.pages.model.TablePageModel;
import com.vahan.web.pages.session.SecureWebSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class WaiterPage extends WebPage {

    private static final long serialVersionUID = -6821508150682082294L;

    private OrderPageModel orderPageModel;

    private TablePageModel tablePageModel;

    private ProductPageModel productPageModel;

    private ProductInOrderPageModel productInOrderPageModel;

    @SpringBean
    private ManagerHelper managerHelper;

    @SpringBean
    private WaiterPageHelper waiterPageHelper;

    public WaiterPage() {
        orderPageModel = new OrderPageModel();
        tablePageModel = new TablePageModel();
        productPageModel = new ProductPageModel();
        productInOrderPageModel = new ProductInOrderPageModel();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Link toHomePageLink = new Link("toHomePage") {

            private static final long serialVersionUID = -3872707863411905747L;

            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        };

        add(toHomePageLink);

        add(new Label("message", "hello " + SecureWebSession.getHttpSession().getUser().getName()));


        CreateOrderContainer createOrderContainer = new CreateOrderContainer("createOrderContainer", new Model<>());
        createOrderContainer.setOutputMarkupId(true);
        add(createOrderContainer);



    }

    private class CreateOrderContainer extends WebMarkupContainer {

        private static final long serialVersionUID = 3123925729569198793L;

        public CreateOrderContainer(String id, IModel<?> model) {
            super(id, model);

            FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
            feedbackPanel.setOutputMarkupId(true);
            add(feedbackPanel);

            Form orderCreateForm = new Form("orderCreateForm");
            add(orderCreateForm);

            orderCreateForm.add(new AjaxButton("orderCreateConfirm") {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    super.onSubmit(target, form);

                    if (SecureWebSession.getHttpSession().getUser().getUserType() == UserType.MANAGER  ) {
                        info("manager can not create order");
                    }
                    else if (SecureWebSession.getHttpSession().getUser().getTable() == null){
                        info("current waiter is not attached to table");
                    }
                    else if (SecureWebSession.getHttpSession().getUser().getUserType() == UserType.WAITER){

                        waiterPageHelper.createOrder();

                        waiterPageHelper.changeListOfOrders(orderPageModel);

                        info("order created");
                    }
                    target.add(feedbackPanel);
                }
            });

            Form productInOrderCreateForm = new Form("productInOrderCreateForm");
            add(productInOrderCreateForm);

            ChoiceRenderer<Product> productChoiceRenderer = new ChoiceRenderer<>("name");
            DropDownChoice<Product> productDropDownChoice = new DropDownChoice<Product>("productDropDownChoice", new PropertyModel<>(productInOrderPageModel, "product"), waiterPageHelper.getAllProducts(productPageModel), productChoiceRenderer);
            productInOrderCreateForm.add(productDropDownChoice);

            DropDownChoice<Integer> piecesDropDownChoice = new DropDownChoice<Integer>("piecesDropDownChoice", new PropertyModel<>(productInOrderPageModel, "pieces"), waiterPageHelper.productPieces());
            productInOrderCreateForm.add(piecesDropDownChoice);

            productDropDownChoice.setRequired(true);
            piecesDropDownChoice.setRequired(true);

            productInOrderCreateForm.add(new AjaxButton("confirmProductInOrder") {

                private static final long serialVersionUID = 8120655179351618925L;

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    super.onSubmit(target, form);

                    if (SecureWebSession.getHttpSession().getUser().getUserType() == UserType.MANAGER) {

                        info("manager can not create product in order (only waiter)");


                    }
                    else if (SecureWebSession.getHttpSession().getUser().getTable() == null){
                        info("waiter is not attached to table");
                    }
                    else if (SecureWebSession.getHttpSession().getUser().getTable().getOrder() != null){
                        info("first create order for current waiter");
                    }
                    else if (SecureWebSession.getHttpSession().getUser().getTable().getOrder() != null){
                        waiterPageHelper.createProductInOrder(productInOrderPageModel);

                        info("product in order was created");
                    }
                    target.add(feedbackPanel);

                }
            });
        }
    }

}



