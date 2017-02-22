package com.vahan.service.order;

/**
 * Created by vahan on 2/17/17.
 */
import com.vahan.model.order.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order saveOrder(Order order);
}
