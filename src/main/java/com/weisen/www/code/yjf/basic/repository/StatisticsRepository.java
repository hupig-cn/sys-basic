package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Statistics;


/**
 * Spring Data  repository for the Statistics entity.
 */
@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

}
