package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_ReceiptpayRepository extends JpaRepository<Receiptpay, Long> {

	// 查询用户的明细
	List<Receiptpay> getReceiptpayByUserid(String userid);
	
	List<Receiptpay> getReceiptpayByUseridAndDealtype(String userid,String dealtype);

	// 根据用户id，时间范围,交易类型 查询明细
	@Query(value = "select id,dealtype,userid,sourcer,benefit,amount,bonus,happendate,freezedate,dealstate,"
			+ "creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other "
			+ "from receiptpay where userid=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
			+ "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype = ?4", nativeQuery = true)
	List<Receiptpay> getReceiptpayByUseridAndTime(String userId, String startTime, String endTime, String dealtype);

	//总收益
	List<Receiptpay> findAllByUseridAndDealtype(String userId,String dealtype);

	// 根据类型，查询时间段内的收益数据
    @Query(value = "select id,dealtype,userid,sourcer,benefit,amount,bonus,happendate,freezedate,dealstate,"
        + "creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other "
        + "from receiptpay where userid=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
        + "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype in (?4,?5)", nativeQuery = true)
    List<Receiptpay> findInfoByTime(String userId, String startTime, String endTime, String four,String five);

    @Query(value = "select id,dealtype,userid,sourcer,benefit,amount,bonus,happendate,freezedate,dealstate,"
        + "creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other "
        + "from receiptpay where userid=?1 and dealtype in (?2,?3)", nativeQuery = true)
    List<Receiptpay> getAllInfo(String userid,String two,String three);
}
