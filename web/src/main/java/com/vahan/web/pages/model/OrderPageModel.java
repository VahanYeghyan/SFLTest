package com.vahan.web.pages.model;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.order.Status;
import com.vahan.model.order.Order;
import com.vahan.model.productInOrder.ProductInOrder;
import com.vahan.model.table.Table;
import com.vahan.model.user.User;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class OrderPageModel implements Serializable{

    private static final long serialVersionUID = 5575693060030968285L;

    private int id;

    private User user;

    private Status status;

    private List<Order> ordersList;

    private List<ProductInOrder> productInOrderList;

    private Table table;

    public OrderPageModel() {

        ordersList = new LinkedList<>();

        productInOrderList = new LinkedList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public List<Order> getFilteredOrders(){
        return ordersList;
    }

    public List<ProductInOrder> getProductInOrderList() {
        return productInOrderList;
    }

    public void setProductInOrderList(List<ProductInOrder> productInOrderList) {
        this.productInOrderList = productInOrderList;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
