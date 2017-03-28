package com.vahan.model.order;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.productInOrder.ProductInOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",cascade = CascadeType.ALL)
    private Set<ProductInOrder> productInOrderSet = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private com.vahan.model.table.Table table;

    @Column(name = "amount")
    private int amount;


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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new EqualsBuilder()
                .append(getId(), order.getId())
                .append(getAmount(), order.getAmount())
                .append(getProductInOrderSet(), order.getProductInOrderSet())
                .append(getTable(), order.getTable())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getProductInOrderSet())
                .append(getTable())
                .append(getAmount())
                .toHashCode();
    }
}
