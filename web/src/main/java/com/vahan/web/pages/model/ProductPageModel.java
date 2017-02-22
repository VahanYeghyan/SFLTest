package com.vahan.web.pages.model;

import java.io.Serializable;

/**
 * Created by vahan on 2/17/17.
 */
public class ProductPageModel implements Serializable{

    private static final long serialVersionUID = -7832036551680369674L;

    private int id;

    private String name;

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
}
