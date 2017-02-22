package com.vahan.model.table;

import com.vahan.model.order.Order;
import com.vahan.model.user.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vahan on 2/17/17.
 */
@Entity
@javax.persistence.Table(name = "tables",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "user_id"}))
public class Table implements Serializable {


    private static final long serialVersionUID = -9031824923773935958L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    User user;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
