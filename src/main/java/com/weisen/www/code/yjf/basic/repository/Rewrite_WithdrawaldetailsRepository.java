package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Withdrawaldetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_WithdrawaldetailsRepository extends JpaRepository<Withdrawaldetails, Long> {

    // 分页查询用户提现记录
    @Query(value = "select id,userid,withdrawalway,withdrawalid,type,amount,afteramount,createdate,modifierdate,state " +
        "from withdrawaldetails where userid = ?1 order by createdate DESC limit ?2,?3",nativeQuery = true)
    List<Withdrawaldetails> getAllUserInfo(String userid,int index,int pageSize);

    @Query(value = "select count(*) " +
        "from withdrawaldetails where userid = ?1",nativeQuery = true)
    int getAllUserInfoCount(String userid);

    Withdrawaldetails findByWithdrawalid(String withdrawalid);

}
