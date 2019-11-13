package com.weisen.www.code.yjf.basic.repository.rewrite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Spring Data  repository for the Order entity.
 */
@Repository
public interface Rewrite_OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "select * from shopmall.jhi_order where bigorder = ?1 ",nativeQuery = true)
    List<Order> findOrderByBigorder(String Bigorder);

    @Transactional
    @Modifying(clearAutomatically = true)
    //DELETE FROM 表名称 WHERE 列名称 = 值
    @Query(value = "insert into shopmall.jhi_order (bigorder,ordernum,state, userid, commodityid,specificationsid, consignee, mobile, address,num,  paymethod, payresult, creator, createdate) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14) ",nativeQuery = true)
    void baocun(String bigorder, String ordernum, String state, String userid, String commodityid, String specificationsid, String consignee, String mobile, String address, String num, String paymethod, String payresult, String creator, String createdate);
}
