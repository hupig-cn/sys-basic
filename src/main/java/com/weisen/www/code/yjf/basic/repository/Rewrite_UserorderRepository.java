package com.weisen.www.code.yjf.basic.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Userorder;

@Repository
public interface Rewrite_UserorderRepository extends JpaRepository<Userorder, Long> {
	
	// 分页查询订单列表
    @Query(value = "select id,ordercode,orderstatus,sum,userid,payee,payway,payresult,paytime,concession,rebate,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other,express_company,express_no "
    		+ "from userorder where (?1 is null or userid = ?1) and (?2 is null or ordercode = ?2) and (?3 is null or orderstatus = ?3) and other > 1 order by createdate DESC limit ?4,?5",nativeQuery = true)
    List<Userorder> getOrderList(String userid,String ordercode,String orderstatus,int pageNum,int pageSize);
    

	@Query(value = "select a.ordercode,a.userid,c.model,c.specifications,c.price,b.num,a.sum,b.consignee,b.mobile,b.address,a.orderstatus,a.payway,a.paytime,a.express_no,a.express_company "
			+ "from userorder a left join shopmall.jhi_order b on b.bigorder = a.id "
			+ "left join shopmall.specifications c on c.id = a.other "
			+ "where (?1 is null or userid = ?1) and (?2 is null or ordercode = ?2) "
			+ "and (?3 is null or orderstatus = ?3) and other > 1 order by createdate DESC limit ?4,?5",nativeQuery = true)
	List<Map<String,Object>> getOrderList2(String userid,String ordercode,String orderstatus,int pageNum,int pageSize);

	// 分页查询订单列表
    @Query(value = "select count(*) from userorder where (?1 is null or userid = ?1) and (?2 is null or ordercode = ?2) and (?3 is null or orderstatus = ?3) and other > 1",nativeQuery = true)
    int getOrderListCount(String userid,String ordercode,String orderstatus);

    //根据收款id查询订单
    @Query(value = "select * from userorder where payee = ?1 and orderstatus = 2",nativeQuery = true)
    List<Userorder> findAllByPayee(String payeeId);

    List<Userorder> findAllByUserid(String userId);

    //根据用户id和订单状态查询订单
    List<Userorder> findAllByPayeeAndOrderstatus(String payeeId,String orderstatus);

    //根据用户id和订单状态查询订单
    List<Userorder> findAllByUseridAndOrderstatus(String userid,String orderstatus);


    @Query(value = "select * from userorder where userid=?1 And paytime Is Not Null Order by payTime DESC ", nativeQuery = true)
    List<Userorder> findByTimeAndUserid(String userid);

    @Query(value = "select * "
        + "from userorder where payee =?1 and paytime > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
        + "and paytime < str_to_date(?3,'%Y-%m-%d %H:%i:%s') ", nativeQuery = true)
    List<Userorder> findByTimeAndPayee (String payee , String startTime, String endTime);

    Userorder findUserorderById(Long id);
    
    //查找营业额
    @Query(value = "select * from userorder where payee =?1 and orderstatus = 2 and paytime between ?2 and ?3", nativeQuery = true)
    List<Userorder> findByPayeeAndTime (String payee , String startTime, String endTime);
}
