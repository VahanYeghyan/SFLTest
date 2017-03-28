package com.vahan.web.pages.helper.interfaces;

import com.vahan.model.order.Order;
import com.vahan.model.product.Product;
import com.vahan.web.pages.model.OrderPageModel;
import com.vahan.web.pages.model.ProductInOrderPageModel;
import com.vahan.web.pages.model.ProductPageModel;

import java.util.List;

/**
 * Created by vahan on 2/20/17.
 */
public interface WaiterPageHelper {

    List<Order> getAllOrders(OrderPageModel orderPageModel);

    Order saveOrder(OrderPageModel orderPageModel);

    void changeListOfOrders(OrderPageModel orderPageModel);

    List<Product> getAllProducts(ProductPageModel productPageModel);

    List<Integer> productPieces();

    void createProductInOrder(ProductInOrderPageModel productInOrderPageModel);

    void createOrder();
}
