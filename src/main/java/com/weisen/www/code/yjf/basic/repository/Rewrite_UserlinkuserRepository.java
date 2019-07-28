package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_UserlinkuserRepository extends JpaRepository<Userlinkuser, Long> {

    @Query(value = "from Userlinkuser where userid = ?1")
    Userlinkuser findByUserId(Long userId);
    
    Userlinkuser findByUserid(String userid);

    long countAllByRecommendid(String recommendid);

    @Query(value = "select ",nativeQuery = true)
    List<Userlinkuser> findAllByTime();

    long countAllByCreatedateBetweenAndRecommendid(String startTime ,String endTime,String recommendid);

}
