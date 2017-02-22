package com.vahan.web.pages.model;

import com.vahan.model.table.Table;
import com.vahan.model.user.User;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vahan on 2/17/17.
 */
public class TablePageModel implements Serializable {

    private static final long serialVersionUID = 4231533864137560800L;
    private int id;

    private int number;

    private List<Table> tableList;

    private User user;

    private List<Integer> numberList;

    /*Constructors*/
    public TablePageModel() {

        tableList = new LinkedList<>();

        numberList = new LinkedList<>();
    }

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

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    public List<Table> getFilteredTables() {
        return tableList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Integer> numberList) {
        this.numberList = numberList;
    }
}
