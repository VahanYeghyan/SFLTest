package com.vahan.model.user;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.order.Order;
import com.vahan.model.table.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -4776432905734406007L;
    /*Properties*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "userType", nullable = false)
    private UserType userType;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "removed")
    private LocalDateTime removed;

    @Column(name = "updated")
    private LocalDateTime updated;

    @OneToOne(mappedBy = "user")
    private com.vahan.model.table.Table table;

    /*Constructor*/
    public User() {
        created = LocalDateTime.now();
        setUpdated(getCreated());
    }

    /*Getters and setters*/
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getRemoved() {
        return removed;
    }

    public void setRemoved(LocalDateTime removed) {
        this.removed = removed;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public com.vahan.model.table.Table getTable() {
        return table;
    }

    public void setTable(com.vahan.model.table.Table table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(getId(), user.getId())
                .append(getName(), user.getName())
                .append(getUsername(), user.getUsername())
                .append(getPassword(), user.getPassword())
                .append(getUserType(), user.getUserType())
                .append(getCreated(), user.getCreated())
                .append(getRemoved(), user.getRemoved())
                .append(getUpdated(), user.getUpdated())
                .append(getTable(), user.getTable())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getUsername())
                .append(getPassword())
                .append(getUserType())
                .append(getCreated())
                .append(getRemoved())
                .append(getUpdated())
                .append(getTable())
                .toHashCode();
    }
}
