package com.vahan.repository.productInOrder;

import com.vahan.model.productInOrder.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vahan on 2/17/17.
 */
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {
}
