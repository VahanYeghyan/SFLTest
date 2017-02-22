package com.vahan.model.productInOrder;

import com.vahan.model.order.Order;
import com.vahan.model.product.Product;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vahan on 2/17/17.
 */
@Entity
@Table(name="product_in_order")
public class ProductInOrder implements Serializable{

    private static final long serialVersionUID = 1545541005049928924L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(name = "amount")
    private int amount;

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
