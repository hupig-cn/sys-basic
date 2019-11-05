package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;


/**
 * Spring Data  repository for the Receiptpay entity.
 */
@Repository
public interface ReceiptpayRepository extends JpaRepository<Receiptpay, Long> {

}
