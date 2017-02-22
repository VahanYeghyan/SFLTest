package com.vahan.service.table.impl;

import com.vahan.model.table.Table;
import com.vahan.repository.tables.TablesRepository;
import com.vahan.service.table.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by vahan on 2/17/17.
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TablesRepository tablesRepository;

    @PersistenceContext
    private EntityManager em;

    //saving table
    @Override
    public void saveTable(Table table) {

        tablesRepository.save(table);
    }

    //getting whole table list
    @Override
    public List<Table> tableList() {
        return tablesRepository.findAll();
    }

    @Override
    public Table getTableByNumber(int number) {
        return tablesRepository.getTableByNumber(number);
    }

    @Override
    public List<Integer> tableNumbers() {
        
        String query = "select number from tables";

        Query query1 = em.createNativeQuery(query);

        return (List<Integer>) query1.getResultList();
    }
}
