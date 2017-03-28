package com.vahan.model.product;

import com.vahan.model.productInOrder.ProductInOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Column(name = "name",unique = true)
    private String name;

    @Column(name="price")
    private int price;

    @OneToOne(mappedBy = "product")
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(getId(), product.getId())
                .append(getPrice(), product.getPrice())
                .append(getName(), product.getName())
                .append(getProductInOrder(), product.getProductInOrder())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getPrice())
                .append(getProductInOrder())
                .toHashCode();
    }
}
