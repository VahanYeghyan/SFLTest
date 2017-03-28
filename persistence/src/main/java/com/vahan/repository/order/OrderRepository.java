package com.vahan.repository.order;

/**
 * Created by vahan on 2/17/17.
 */

import com.vahan.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order getOrderByTableNumber(int i);
}
