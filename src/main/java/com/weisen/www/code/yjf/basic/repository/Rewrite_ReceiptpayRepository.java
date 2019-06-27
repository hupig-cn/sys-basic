package com.weisen.www.code.yjf.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;

@Repository
public interface Rewrite_ReceiptpayRepository extends JpaRepository<Receiptpay, Long> {

	// 查询用户的明细
	List<Receiptpay> getReceiptpayByUserid();

	// 根据用户id，时间范围,交易类型 查询明细
	@Query(value = "select id,dealtype,userid,sourcer,benefit,amount,bonus,happendate,freezedate,dealstate,"
			+ "creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other "
			+ "from receiptpay where userid=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
			+ "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype = ?4", nativeQuery = true)
	List<Receiptpay> getReceiptpayByUseridAndTime(String userId, String startTime, String endTime, String dealtype);
}
