package com.vahan.model.product;

import com.vahan.model.productInOrder.ProductInOrder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vahan on 2/17/17.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable{

    private static final long serialVersionUID = -4805571123894904394L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ProductInOrder productInOrder;

    //getters and setters

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
}
