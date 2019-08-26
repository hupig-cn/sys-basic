package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Receiptpay entity.
 */
@Repository
public interface ReceiptpayRepository extends JpaRepository<Receiptpay, Long> {

}
