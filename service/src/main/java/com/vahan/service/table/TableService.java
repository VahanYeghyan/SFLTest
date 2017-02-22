package com.vahan.service.table;

import com.vahan.model.table.Table;

import java.util.List;

/**
 * Created by vahan on 2/17/17.
 */
public interface TableService {

    void saveTable(Table table);

    List<Table> tableList();

    Table getTableByNumber(int number);

    List<Integer> tableNumbers();
}
