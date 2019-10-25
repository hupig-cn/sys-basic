package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_001_UserorderRepository extends JpaRepository<Userorder, Long> {

    @Query(value = "select * "
        + "from userorder where userid=?1 and paytime > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
        + "and paytime < str_to_date(?3,'%Y-%m-%d %H:%i:%s') ", nativeQuery = true)
    List<Userorder> findByTimeAndUserid(String userid, String startTime, String endTime);

    @Query(value = "select * "
        + "from userorder where userid=?1 and other > 1 ", nativeQuery = true)
    List<Userorder> findUserorderByUserid(String userid);
}
