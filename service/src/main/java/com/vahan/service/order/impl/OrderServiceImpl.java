package com.vahan.service.order.impl;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.order.Order;
import com.vahan.repository.order.OrderRepository;
import com.vahan.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;



@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;


    //getting all orders
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    //saving order
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderByTableNumber(int i) {

        return orderRepository.getOrderByTableNumber(i);
    }
}
