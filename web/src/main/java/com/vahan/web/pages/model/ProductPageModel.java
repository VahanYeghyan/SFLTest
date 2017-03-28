package com.vahan.web.pages.model;

import com.vahan.model.product.Product;
import com.vahan.model.productInOrder.ProductInOrder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vahan on 2/17/17.
 */
public class ProductPageModel implements Serializable{

    private static final long serialVersionUID = -7832036551680369674L;

    private int id;

    private String name;

    private int price;

    private ProductInOrder productInOrder;

    private List<Product> productList;

    public ProductPageModel() {
        productList = new LinkedList<>();
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

    public ProductInOrder getProductInOrder() {
        return productInOrder;
    }

    public void setProductInOrder(ProductInOrder productInOrder) {
        this.productInOrder = productInOrder;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
