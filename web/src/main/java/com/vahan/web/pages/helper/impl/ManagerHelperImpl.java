package com.vahan.web.pages.helper.impl;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.product.Product;
import com.vahan.model.table.Table;
import com.vahan.model.user.User;
import com.vahan.model.user.UserType;
import com.vahan.repository.products.ProductsRepository;
import com.vahan.repository.tables.TablesRepository;
import com.vahan.service.table.TableService;
import com.vahan.service.user.UserService;
import com.vahan.web.pages.helper.interfaces.ManagerHelper;
import com.vahan.web.pages.model.ProductPageModel;
import com.vahan.web.pages.model.TablePageModel;
import com.vahan.web.pages.model.UserPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

@Service
public class ManagerHelperImpl implements ManagerHelper,Serializable {

    private static final long serialVersionUID = -353088700420851271L;

    @Autowired
    private UserService userService;

    @Autowired
    private TableService tableService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private TablesRepository tablesRepository;


    //getting whole user list
    @Override
    public List<User> getAllUsers(UserPageModel userPageModel) {

        if (userPageModel.getFilteredUsers().isEmpty()) {

            userPageModel.getFilteredUsers().addAll(userService.usersList());
        }

        return userPageModel.getFilteredUsers();
    }

    //getting only waiters
    @Override
    public List<User> getAllWaiters(UserPageModel userPageModel) {

        if (userPageModel.getWaitersList().isEmpty()) {

            userPageModel.getWaitersList().addAll(userService.getOnlyWaiters());
        }

        return userPageModel.getWaitersList();
    }

    @Override
    public void changeListOfWaiters(UserPageModel userPageModel) {

        userPageModel.getWaitersList().clear();

        userPageModel.getWaitersList().addAll(userService.getOnlyWaiters());
    }

    //changing list of users
    @Override
    public void changeListOfUsers(UserPageModel userPageModel) {

        userPageModel.getFilteredUsers().clear();

        userPageModel.getFilteredUsers().addAll(userService.usersList());

    }

    public List<Table> changeListOfTables(TablePageModel tablePageModel) {

        tablePageModel.getFilteredTables().clear();

        tablePageModel.getFilteredTables().addAll(tableService.tableList());

        return tablePageModel.getFilteredTables();
    }

    //saving user
    @Override
    public void saveUser(UserPageModel userPageModel) {

        final User user = new User();
        user.setName(userPageModel.getName());
        user.setUsername(userPageModel.getUsername());
        user.setPassword(userPageModel.getPassword());
        user.setUserType(UserType.WAITER);

        userService.saveUser(user);
    }

    //creating table
    @Override
    public void createTable(TablePageModel tablePageModel) {
        Table table = new Table();
        table.setNumber(tablePageModel.getNumber());
        tableService.saveTable(table);

    }

    //creating product
    @Override
    public void createProduct(ProductPageModel productPageModel) {
        Product product = new Product();
        product.setName(productPageModel.getName());
        productsRepository.save(product);

    }

    //getting whole table list
    @Override
    public List<Table> getTableList(TablePageModel tablePageModel) {

        if (tablePageModel.getFilteredTables().isEmpty()) {
            tablePageModel.getFilteredTables().addAll(tableService.tableList());
        }
        return tablePageModel.getFilteredTables();
    }

    //attaching waiter to table
    @Transactional
    @Override
    public void setWaiterToTable(TablePageModel tablePageModel) {

        Assert.notNull(tablePageModel.getUser(), "user must be not null");

        for (Table table1 : tableService.tableList()) {
            if (table1.getUser() != null) {
                if (table1.getUser().getName().equals(tablePageModel.getUser().getName())) {
                    table1.setUser(null);
                    tablesRepository.save(table1);
                }
            }
        }

        Table table = tableService.getTableByNumber(tablePageModel.getNumber());

        table.setUser(tablePageModel.getUser());

        tablesRepository.save(table);
    }


    public List<Integer> tableNumbersList(TablePageModel tablePageModel) {

        if (tablePageModel.getNumberList().isEmpty()) {
            tablePageModel.getNumberList().addAll(tableService.tableNumbers());
        }

        return tablePageModel.getNumberList();
    }

    @Override
    public void changeTableNumberList(TablePageModel tablePageModel) {

        tablePageModel.getNumberList().clear();

        tablePageModel.getNumberList().addAll(tableService.tableNumbers());
    }
}
