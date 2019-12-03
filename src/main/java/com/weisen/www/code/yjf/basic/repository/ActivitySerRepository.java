package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.ActivitySer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivitySer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivitySerRepository extends JpaRepository<ActivitySer, Long> {

}
