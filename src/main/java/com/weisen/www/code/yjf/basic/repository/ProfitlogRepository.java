package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Profitlog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Profitlog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfitlogRepository extends JpaRepository<Profitlog, Long> {

}
