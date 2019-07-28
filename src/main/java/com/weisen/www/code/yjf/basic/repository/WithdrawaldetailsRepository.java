package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Withdrawaldetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Withdrawaldetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WithdrawaldetailsRepository extends JpaRepository<Withdrawaldetails, Long> {

}