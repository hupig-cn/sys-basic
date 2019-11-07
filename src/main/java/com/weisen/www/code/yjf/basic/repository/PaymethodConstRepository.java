package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.PaymethodConst;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PaymethodConst entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymethodConstRepository extends JpaRepository<PaymethodConst, Long> {

}
