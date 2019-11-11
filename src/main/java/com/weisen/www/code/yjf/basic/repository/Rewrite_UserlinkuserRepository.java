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

    @Query(value = "select id,userid,recommendid,partner,province,city,county,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other,metamorphosis_time,pyee " +
        "from userlinkuser" +
        " where recommendid = ?1 order by modifierdate desc limit ?2,?3",nativeQuery = true)
    List<Userlinkuser> findAllByRecommendidAndTime(String recommendid,int indexPage,int pageSize);
}
