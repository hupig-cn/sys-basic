package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Advertisement;
import com.weisen.www.code.yjf.basic.domain.Feedback;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Feedback entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_FeedbackRepository extends JpaRepository<Feedback, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM feedback a WHERE a.logicdelete = 0 ORDER BY a.id DESC LIMIT :startIndex, :displayLength")
	List<Feedback> findList(@Param("startIndex") int startIndex,@Param("displayLength") int displayLength);
	
}
