package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Statistics;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Statistics entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

}
