package com.vahan.model.order;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.productInOrder.ProductInOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 267086812321571206L;

    /*Properties*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int id;

    @OneToMany
    private Set<ProductInOrder> productInOrderSet = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private com.vahan.model.table.Table table;


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<ProductInOrder> getProductInOrderSet() {
        return productInOrderSet;
    }

    public void setProductInOrderSet(Set<ProductInOrder> productInOrderSet) {
        this.productInOrderSet = productInOrderSet;
    }

    public com.vahan.model.table.Table getTable() {
        return table;
    }

    public void setTable(com.vahan.model.table.Table table) {
        this.table = table;
    }
}
