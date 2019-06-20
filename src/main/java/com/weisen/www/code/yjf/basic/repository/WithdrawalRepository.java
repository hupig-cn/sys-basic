package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Withdrawal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

}
