package com.vahan.web.pages;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.order.Order;
import com.vahan.model.table.Table;
import com.vahan.web.pages.helper.interfaces.ManagerHelper;
import com.vahan.web.pages.model.OrderPageModel;
import com.vahan.web.pages.model.TablePageModel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;


public class WaiterPage extends WebPage {

    private static final long serialVersionUID = -6821508150682082294L;

    private OrderPageModel orderPageModel;

    private TablePageModel tablePageModel;

    @SpringBean
    private ManagerHelper managerHelper;

    public WaiterPage() {
        orderPageModel = new OrderPageModel();
        tablePageModel = new TablePageModel();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        CreateOrderContainer createOrderContainer = new CreateOrderContainer("createOrderContainer",new Model<>());
        add(createOrderContainer);

    }

    private class CreateOrderContainer extends WebMarkupContainer{

        public CreateOrderContainer(String id, IModel<?> model) {
            super(id, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            Form orderCreateForm = new Form("orderCreateForm");

            ChoiceRenderer<Table> tableChoiceRenderer = new ChoiceRenderer<>("number");

            DropDownChoice<Table> tableDropDownChoice = new DropDownChoice<>("tableSelect",new PropertyModel<>(orderPageModel,"table"),managerHelper.getTableList(tablePageModel),tableChoiceRenderer);

            add(orderCreateForm);
            orderCreateForm.add(tableDropDownChoice);




        }
    }


}
