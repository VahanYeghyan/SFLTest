package com.vahan.repository.tables;

import com.vahan.model.table.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vahan on 2/17/17.
 */
@Repository
public interface TablesRepository extends JpaRepository<Table, Integer> {

   Table getTableByNumber(int number);

}
