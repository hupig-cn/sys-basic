package com.weisen.www.code.yjf.basic.repository.rewrite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Statistics;


/**
 * Spring Data  repository for the Statistics entity.
 */
@Repository
public interface Rewrite_StatisticsRepository extends JpaRepository<Statistics, Long> {
    @Query(value = "SELECT * FROM statistics WHERE userid = ?1 ORDER BY createdate DESC LIMIT 0,4",nativeQuery = true)
    List<Statistics> findByUserId(Long userId);
}
