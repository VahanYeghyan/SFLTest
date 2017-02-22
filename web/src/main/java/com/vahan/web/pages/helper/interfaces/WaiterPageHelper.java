package com.vahan.web.pages.helper.interfaces;

import com.vahan.model.order.Order;
import com.vahan.web.pages.model.OrderPageModel;

import java.util.List;

/**
 * Created by vahan on 2/20/17.
 */
public interface WaiterPageHelper {

    List<Order> getAllOrders(OrderPageModel orderPageModel);

    Order saveOrder(OrderPageModel orderPageModel);

    void changeListOfOrders(OrderPageModel orderPageModel);
}
