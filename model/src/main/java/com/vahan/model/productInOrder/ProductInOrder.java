package com.vahan.model.productInOrder;

import com.vahan.model.order.Order;
import com.vahan.model.product.Product;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private int amount;

    @Column(name = "pieces")
    private int pieces;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProductInOrder that = (ProductInOrder) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getAmount(), that.getAmount())
                .append(getPieces(), that.getPieces())
                .append(getProduct(), that.getProduct())
                .append(getOrder(), that.getOrder())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getProduct())
                .append(getAmount())
                .append(getPieces())
                .append(getOrder())
                .toHashCode();
    }
}
