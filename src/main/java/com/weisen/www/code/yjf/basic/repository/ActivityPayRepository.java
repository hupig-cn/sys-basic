package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.ActivityPay;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivityPay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityPayRepository extends JpaRepository<ActivityPay, Long> {
	
	//根据用户id查询流水
	@Query(value = "select * from activity_pay where user_id = ?1 and type = 1 and create_time between ?2 and ?3 order by create_time desc limit ?4,?5", nativeQuery = true)
	List<ActivityPay> findByUserId(String Userid, String firstTime,String lastTime,Integer indexPage, Integer pageSize);
}
