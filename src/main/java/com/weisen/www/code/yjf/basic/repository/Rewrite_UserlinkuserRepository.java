package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Rewrite_UserlinkuserRepository extends JpaRepository<Userlinkuser, Long> {

	@Query(value = "from Userlinkuser where userid = ?1")
	Userlinkuser findByUserId(Long userId);

	Userlinkuser findByUserid(String userid);

	long countAllByRecommendid(String recommendid);

	@Query(value = "select ", nativeQuery = true)
	List<Userlinkuser> findAllByTime();

	long countAllByCreatedateBetweenAndRecommendid(String startTime, String endTime, String recommendid);

	@Query(value = "select * from userlinkuser"
			+ " where recommendid = ?1 order by modifierdate desc limit ?2,?3", nativeQuery = true)
	List<Userlinkuser> findAllByRecommendidAndTime(String recommendid, int indexPage, int pageSize);

	// 查询推荐榜前十位用户
	@Query(value = "select l.first_name,u.id,u.recommendid,count(recommendid) as total from userlinkuser u " + 
			"join login.jhi_user l on u.recommendid=l.id where recommendid is NOT NULL and recommendid <> '' GROUP BY recommendid order by total desc limit 0,10", nativeQuery = true)
	List<Map<String, Object>> getRecommendisList();
}
