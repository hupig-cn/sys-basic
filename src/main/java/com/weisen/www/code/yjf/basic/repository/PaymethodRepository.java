package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Paymethod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Paymethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymethodRepository extends JpaRepository<Paymethod, Long> {

}
