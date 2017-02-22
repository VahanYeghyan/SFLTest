package com.vahan.web.pages.helper.interfaces;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.order.Order;
import com.vahan.model.table.Table;
import com.vahan.model.user.User;
import com.vahan.web.pages.model.OrderPageModel;
import com.vahan.web.pages.model.ProductPageModel;
import com.vahan.web.pages.model.TablePageModel;
import com.vahan.web.pages.model.UserPageModel;

import java.util.List;

public interface ManagerHelper {


    void saveUser(UserPageModel userPageModel);

    List<User> getAllUsers(UserPageModel userPageModel);

    List<User> getAllWaiters(UserPageModel userPageModel);

    void changeListOfUsers(UserPageModel userPageModel);



    void createTable(TablePageModel tablePageModel);

    void createProduct(ProductPageModel productPageModel);

    List<Table> getTableList(TablePageModel tablePageModel);

    List<Table> changeListOfTables(TablePageModel tablePageModel);

    void setWaiterToTable(TablePageModel tablePageModel);

    List<Integer> tableNumbersList(TablePageModel tablePageModel);

    void changeTableNumberList(TablePageModel tablePageModel);

    void changeListOfWaiters(UserPageModel userPageModel);


}
