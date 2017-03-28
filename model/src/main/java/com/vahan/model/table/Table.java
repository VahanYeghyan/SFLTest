package com.vahan.model.table;

import com.vahan.model.order.Order;
import com.vahan.model.user.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vahan on 2/17/17.
 */
@Entity
@javax.persistence.Table(name = "tables")
public class Table implements Serializable {


    private static final long serialVersionUID = -9031824923773935958L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "number",unique = true)
    private int number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "table")
    private Order order;

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return new EqualsBuilder()
                .append(getId(), table.getId())
                .append(getNumber(), table.getNumber())
                .append(getUser(), table.getUser())
                .append(getOrder(), table.getOrder())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getNumber())
                .append(getUser())
                .append(getOrder())
                .toHashCode();
    }
}
