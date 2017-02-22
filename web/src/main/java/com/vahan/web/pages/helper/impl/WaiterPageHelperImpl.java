package com.vahan.web.pages.helper.impl;

import com.vahan.model.order.Order;
import com.vahan.service.order.OrderService;
import com.vahan.service.table.TableService;
import com.vahan.web.pages.helper.interfaces.WaiterPageHelper;
import com.vahan.web.pages.model.OrderPageModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vahan on 2/20/17.
 */
public class WaiterPageHelperImpl implements WaiterPageHelper,Serializable {

    private static final long serialVersionUID = 7823502748334475672L;
    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;


    //changing list of orders
    @Override
    public void changeListOfOrders(OrderPageModel orderPageModel) {

        orderPageModel.getFilteredOrders().clear();

        orderPageModel.getFilteredOrders().addAll(orderService.getAllOrders());

    }

    //getting whole list of orders
    @Override
    public List<Order> getAllOrders(OrderPageModel orderPageModel) {
        orderPageModel.getFilteredOrders().addAll(orderService.getAllOrders());
        return orderPageModel.getFilteredOrders();
    }

    //saving order
    @Override
    public Order saveOrder(OrderPageModel orderPageModel) {
        final Order order = new Order();

        return orderService.saveOrder(order);
    }


}
