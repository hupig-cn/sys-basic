package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.ActivityCon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivityCon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityConRepository extends JpaRepository<ActivityCon, Long> {
}
