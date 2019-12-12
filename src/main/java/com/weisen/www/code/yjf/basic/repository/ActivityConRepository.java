package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.ActivityCon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the ActivityCon entity.
 */
@Repository
public interface ActivityConRepository extends JpaRepository<ActivityCon, Long> {

	// 根据活动名称和活动是否开关,查找该活动的最低转出余额数据
	@Query(value = "select * from activity_con where activity_name = ?1 and logical_del = 1", nativeQuery = true)
	ActivityCon findActivityConByActivityNameAndLogicalDel(String activityName);
}
