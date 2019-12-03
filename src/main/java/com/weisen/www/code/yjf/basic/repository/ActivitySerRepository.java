package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.ActivitySer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivitySer entity.
 */
@Repository
public interface ActivitySerRepository extends JpaRepository<ActivitySer, Long> {
	
	// 根据商家用户ID查找商家可用资金和活动资金
	ActivitySer findByUserId(String userId);
}
