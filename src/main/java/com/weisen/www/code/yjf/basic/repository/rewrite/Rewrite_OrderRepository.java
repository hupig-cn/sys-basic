package com.weisen.www.code.yjf.basic.repository.rewrite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Order;


/**
 * Spring Data  repository for the Order entity.
 */
@Repository
public interface Rewrite_OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "select * from shopmall.jhi_order where bigorder = ?1 ",nativeQuery = true)
    Order findOrderByBigorder(String Bigorder);
}
