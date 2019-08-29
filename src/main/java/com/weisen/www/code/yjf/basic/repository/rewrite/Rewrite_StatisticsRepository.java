package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Statistics entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_StatisticsRepository extends JpaRepository<Statistics, Long> {
    @Query(value = "SELECT * FROM statistics WHERE userid = ?1 ORDER BY createdate DESC LIMIT 0,4",nativeQuery = true)
    List<Statistics> findByUserId(Long userId);
}
