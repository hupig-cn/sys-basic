package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.ActivityPay;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the ActivityPay entity.
 */
@Repository
public interface ActivityPayRepository extends JpaRepository<ActivityPay, Long> {

	// 查询商家可用资金提现流水明细 LuoJinShui
	@Query(value = "select * from activity_pay where user_id = ?1 and type = 3 ORDER BY create_time DESC LIMIT ?2,?3", nativeQuery = true)
	List<ActivityPay> findByUserIdAndType(String userId, Integer pageNum, Integer pageSize);

}
