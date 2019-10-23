package com.weisen.www.code.yjf.basic.repository.rewrite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weisen.www.code.yjf.basic.domain.Userlinkuser;

public interface Rewrite_IncomeDetailsRepository  extends JpaRepository<Userlinkuser, Long> {
	
	//根据uid找到推荐人数据条数
	@Query(value = "select count(recommendid) from userlinkuser where userid = ?1 and recommendid != 1", nativeQuery = true)
	Long findByUserIdCount(String userId);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	
	//根据uid找到该条信息
	Userlinkuser findByUserid(String userId);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
}
