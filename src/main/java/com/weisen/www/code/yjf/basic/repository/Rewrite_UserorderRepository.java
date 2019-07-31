package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_UserorderRepository extends JpaRepository<Userorder, Long> {

    //根据收款id查询订单
    List<Userorder> findAllByPayee(String payeeId);

    List<Userorder> findAllByUserid(String userId);

    //根据用户id和订单状态查询订单
    List<Userorder> findAllByPayeeAndOrderstatus(String payeeId,String orderstatus);

    //根据用户id和订单状态查询订单
    List<Userorder> findAllByUseridAndOrderstatus(String userid,String orderstatus);

}
