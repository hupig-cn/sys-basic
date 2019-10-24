package com.weisen.www.code.yjf.basic.repository.rewrite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;

public interface Rewrite_IncomeDetailsRepository  extends JpaRepository<Userlinkuser, Long> {
	
	//根据uid找到推荐人数据条数
	@Query(value = "select count(userid) from userlinkuser where recommendid = ?1", nativeQuery = true)
	Long findByRecommendIdCount(String recommendid);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	
	//根据用户uid找到信息
	@Query(value = "select userid,createdate from userlinkuser where recommendid = ?1 and userid != 1 limit ?2 , ?3", nativeQuery = true)
	List<Userlinkuser> findByRecommendIdAndPage(String userId,Integer pageNum,Integer pagesize);
	
	//根据用户id和时间段找到信息,当userid等于1或者空时，相当于没有推荐人
	@Query(value = "select userid,createdate from userlinkuser where recommendid = ?1 and createdate between  ?2 and  ?3 limit ?4 , ?5", nativeQuery = true)
	List<Userlinkuser> findByRecommendIdAndTimeAndPage(String recommendid,String firstTime,String lastTime,Integer pageNum,Integer pagesize);
	
}
