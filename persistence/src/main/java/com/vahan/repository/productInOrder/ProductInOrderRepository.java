package com.vahan.repository.productInOrder;

import com.vahan.model.productInOrder.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vahan on 2/17/17.
 */
@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {
}
