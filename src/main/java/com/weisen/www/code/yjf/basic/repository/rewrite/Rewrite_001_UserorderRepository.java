package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_001_UserorderRepository extends JpaRepository<Userorder, Long> {

    @Query(value = "select * from userorder where userid=?1 and other >= 1 ORDER by createdate desc", nativeQuery = true)
    List<Userorder> findUserorderByUserid(String userid);

    @Query(value = "select * from userorder where userid=?1 and other >= 1 and orderstatus = ?2  ORDER BY createdate DESC", nativeQuery = true)
    List<Userorder> findUserorderByUseridAndOrderstatus(String userid,String orderstatus);

    Userorder findUserorderByOrdercode(String ordercode);
}
