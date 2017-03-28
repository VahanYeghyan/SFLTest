package com.vahan.web.pages.helper.impl;

import com.vahan.model.order.Order;
import com.vahan.model.product.Product;
import com.vahan.model.productInOrder.ProductInOrder;
import com.vahan.model.user.UserType;
import com.vahan.service.order.OrderService;
import com.vahan.service.product.ProductService;
import com.vahan.service.productInOrder.ProductInOrderService;
import com.vahan.service.table.TableService;
import com.vahan.web.pages.helper.interfaces.WaiterPageHelper;
import com.vahan.web.pages.model.OrderPageModel;
import com.vahan.web.pages.model.ProductInOrderPageModel;
import com.vahan.web.pages.model.ProductPageModel;
import com.vahan.web.pages.session.SecureWebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vahan on 2/20/17.
 */
@Component
public class WaiterPageHelperImpl implements WaiterPageHelper,Serializable {

    private static final long serialVersionUID = 7823502748334475672L;

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInOrderService productInOrderService;


    //changing list of orders
    @Override
    public void changeListOfOrders(OrderPageModel orderPageModel) {

        orderPageModel.getFilteredOrders().clear();

        orderPageModel.getFilteredOrders().addAll(orderService.getAllOrders());

    }

    //getting whole list of orders
    @Override
    public List<Order> getAllOrders(OrderPageModel orderPageModel) {
        if (orderPageModel.getFilteredOrders().isEmpty()) {
            orderPageModel.getFilteredOrders().addAll(orderService.getAllOrders());
        }
        return orderPageModel.getFilteredOrders();
    }

    //saving order
    @Override
    public Order saveOrder(OrderPageModel orderPageModel) {
        final Order order = new Order();

        return orderService.saveOrder(order);
    }

    //getting whole list of products
    @Override
    public List<Product> getAllProducts(ProductPageModel productPageModel) {

        if (productPageModel.getProductList().isEmpty()){
            productPageModel.getProductList().addAll(productService.productList());
        }
        return productPageModel.getProductList();
    }

    public List<Integer> productPieces(){
        List<Integer> piecesList = new LinkedList<>();
        for (int i = 1; i < 51; i++){
            piecesList.add(i);
        }
        return piecesList;
    }

    //creating product in order making amount
    public void createProductInOrder(ProductInOrderPageModel productInOrderPageModel){

        if (SecureWebSession.getHttpSession().getUser().getUserType() == UserType.WAITER &&
                orderService.getOrderByTableNumber(SecureWebSession.getHttpSession().getUser().getTable().getNumber()) != null){

            ProductInOrder productInOrder = new ProductInOrder();

            productInOrder.setOrder(orderService.getOrderByTableNumber(SecureWebSession.getHttpSession().getUser().getTable().getNumber()));

            productInOrder.setPieces(productInOrderPageModel.getPieces());

            productInOrder.setProduct(productInOrderPageModel.getProduct());

            productInOrder.setAmount(productInOrderPageModel.getPieces() * productInOrderPageModel.getProduct().getPrice());

            productInOrder.getOrder().setAmount(productInOrder.getOrder().getAmount() + productInOrder.getAmount());

            productInOrderService.saveProductInOrder(productInOrder);
        }




    }
    //creating order
    @Override
    public void createOrder() {

        if (SecureWebSession.getHttpSession().getUser().getTable() != null){

            Order order = new Order();

            order.setTable(SecureWebSession.getHttpSession().getUser().getTable());

            orderService.saveOrder(order);
        }


    }
}
