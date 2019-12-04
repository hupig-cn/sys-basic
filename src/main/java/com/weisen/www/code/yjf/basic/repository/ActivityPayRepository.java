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
	@Query(value = "select * from activity_pay where user_id = ?1 and type = ?2 ORDER BY create_time DESC LIMIT ?3,?4", nativeQuery = true)
	List<ActivityPay> findByUserIdAndType(String userId, Integer type, Integer pageNum, Integer pageSize);

	// 根据用户id查询流水
	@Query(value = "select * from activity_pay where user_id = ?1 and type = 1 and create_time between ?2 and ?3 order by create_time desc limit ?4,?5", nativeQuery = true)
	List<ActivityPay> findByUserId(String Userid, String firstTime, String lastTime, Integer indexPage,
			Integer pageSize);
}
