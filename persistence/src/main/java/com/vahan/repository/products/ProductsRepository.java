package com.vahan.repository.products;

import com.vahan.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vahan on 2/17/17.
 */
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
