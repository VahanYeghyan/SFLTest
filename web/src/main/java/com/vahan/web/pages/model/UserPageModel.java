package com.vahan.web.pages.model;

/**
 * Created by vahan on 2/17/17.
 */


import com.vahan.model.order.Order;
import com.vahan.model.table.Table;
import com.vahan.model.user.User;
import com.vahan.model.user.UserType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class UserPageModel implements Serializable {


    private static final long serialVersionUID = 9195765596643677871L;
    private int id;

    private String name;

    private String username;

    private String password;

    private UserType userType;

    private List<User> userList;

    private List<User> waitersList;

    private Table table;

    private Set<Order> orders = new HashSet<>();

    /*Constructors*/
    public UserPageModel() {

        userList = new LinkedList<>();

        waitersList = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<User> getFilteredUsers() {
        return userList;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<User> getWaitersList() {
        return waitersList;
    }
}
