package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Repository
public interface Rewrite_ReceiptpayRepository extends JpaRepository<Receiptpay, Long> {
	
	@Query(value = "SELECT " + 
			"	a.userid," + 
			"	(SELECT name FROM linkuser WHERE userid = a.userid) AS username," + 
			"	(SELECT phone FROM linkuser WHERE userid = a.userid) AS userphone," + 
			"	a.amount," + 
			"	a.dealtype," + 
			"	(CASE a.dealstate WHEN 1 THEN '支出' ELSE '收入' END) AS dealstate," + 
			"	a.createdate," + 
			"	(CASE WHEN (SELECT other FROM linkaccount WHERE userid = a.userid AND accounttype = '支付宝') IS NOT NULL THEN '支付宝'" + 
			"		WHEN (SELECT other FROM linkaccount WHERE userid = a.userid AND accounttype = '微信') IS NOT NULL THEN '微信'" + 
			"		ELSE 'APP注册'	END) AS comefrom" + 
			"FROM receiptpay a" + 
			"WHERE a.userid = ?1 AND dealtype = ?2" + 
			"ORDER BY a.createdate DESC LIMIT ?3,?4",nativeQuery = true)
	List<Map<String,Object>> findReceiptpayList(String userid, int dealtype, int pageIndex, int pageSize);
	
	@Query(value = "SELECT count(*) FROM receiptpay a WHERE a.userid = ?1 AND dealtype = ?2",nativeQuery = true)
	Integer findReceiptpayCount(String userid, int dealtype);

	// 查询用户的明细
	List<Receiptpay> getReceiptpayByUserid(String userid);

	List<Receiptpay> getReceiptpayByUseridAndDealtype(String userid, String dealtype);

//	// 根据用户id，时间范围,交易类型 查询明细
//	@Query(value = "select id,dealtype,userid,sourcer,benefit,amount,bonus,happendate,freezedate,dealstate,"
//			+ "creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other "
//			+ "from receiptpay where userid=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
//			+ "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype = ?4", nativeQuery = true)
//	List<Receiptpay> getReceiptpayByUseridAndTime(String userId, String startTime, String endTime, String dealtype);

	// 根据用户id，时间范围,交易类型 查询明细
	@Query(value = "select * " + "from receiptpay where userid=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
			+ "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype = ?4", nativeQuery = true)
	List<Receiptpay> getReceiptpayByUseridAndTime(String userId, String startTime, String endTime, String dealtype);

	// 总收益
	List<Receiptpay> findAllByUseridAndDealtype(String userId, String dealtype);

	// 根据类型，查询时间段内的收益数据
	@Query(value = "select * " + "from receiptpay where userid=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
			+ "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype in (?4,?5)", nativeQuery = true)
	List<Receiptpay> findInfoByTime(String userId, String startTime, String endTime, String four, String five);

	@Query(value = "select * " + "from receiptpay where userid=?1 and dealtype in (?2,?3)", nativeQuery = true)
	List<Receiptpay> getAllInfo(String userid, String two, String three);

	// 倒叙分页查询商家流水
	@Query(value = "select * from receiptpay where userid = ?1 and dealtype=?2 order by createdate desc limit ?3,?4", nativeQuery = true)
	List<Receiptpay> getAllByMerchantAndType(String userid, String type, int indexPage, int pageSize);

	// 分页查询一个用户的流水(分页)
	@Query(value = "select * from receiptpay where (?1 is null or userid = ?1) order by createdate desc limit ?2,?3 ", nativeQuery = true)
	List<Receiptpay> getAllByUserSomething(String userid, int pageIndex, int pageSize);

	Long countAllByUserid(String userid);

	@Query(value = "SELECT * FROM receiptpay WHERE userid = ?1 AND DATE_FORMAT(createdate,'%Y-%m-%d') = CURRENT_DATE() ORDER BY createdate DESC", nativeQuery = true)
	List<Receiptpay> findAllByUserIdAndDate(Long userId);

	@Query(value = "SELECT * FROM receiptpay WHERE userid = ?1 AND DATE_FORMAT(createdate,'%Y-%m-%d') = DATE_SUB(CURRENT_DATE(),interval 1 day) ORDER BY createdate DESC", nativeQuery = true)
	List<Receiptpay> findYesToDayAsset(Long userId);

	// 根据类型，查询时间段内的收益数据
	@Query(value = "select * " + "from receiptpay where userid=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
			+ "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype != 6  ", nativeQuery = true)
	List<Receiptpay> findByTimeAndUserid(String userId, String startTime, String endTime);

	@Query(value = "select * "
			+ "from receiptpay where sourcer=?1 and createdate > str_to_date(?2,'%Y-%m-%d %H:%i:%s') "
			+ "and createdate < str_to_date(?3,'%Y-%m-%d %H:%i:%s') and dealtype != 6 ", nativeQuery = true)
	List<Receiptpay> findByTimeAndSourcer(String sourcer, String startTime, String endTime);

	Receiptpay findReceiptpayById(Long id);

//	// 根据时间,用户关系表查询用户的明细
//	@Query(value = "select amount FROM receiptpay where userid= ?1 and sourcer = ?2 and dealtype in (9,10) and createdate between ?3 and ?4", nativeQuery = true)
//	List<BigDecimal> getReceiptpayByUseridAndSourcerAndTime(String userid, String sourcer, String firstTime,
//			String lastTime);

	List<Receiptpay> findByUseridAndSourcer(String userid, String sourcer);

	@Query(value = "select * FROM receiptpay where userid = ?1 and dealtype in (9,10) and createdate between ?2 and ?3 order by createdate desc", nativeQuery = true)
	List<Receiptpay> findReceiptpayByUseridAndTime(String userid, String firstTime, String lastTime);
	
	@Query(value = "select * FROM receiptpay where userid = ?1 and dealtype in (9,10) and amount != 0 and createdate between ?2 and ?3 order by createdate desc LIMIT ?4,?5", nativeQuery = true)
	List<Receiptpay> findReceiptpayByUseridAndTimeAndPage(String userid, String firstTime, String lastTime,Integer pageNum,Integer pageSize);

	@Query(value = "select amount FROM receiptpay where userid = ?1 and dealtype in (9,10)", nativeQuery = true)
	List<BigDecimal> findReceiptpayByUserid(String userid);

	// 分页查询用户积分支出收入按最新时间排序
	@Transactional
	@Query(value = "select * FROM receiptpay where userid = ?1 and amount>0 and dealtype in (5,6) ORDER BY createdate DESC LIMIT ?2,?3 ", nativeQuery = true)
	List<Receiptpay> findByUserid(String userId, Integer pageNum, Integer pageSize);

	// 查询区县数据
	@Transactional
	@Query(value = "select * FROM receiptpay where userid = ?1 and amount>0 and dealtype in (7,8) ORDER BY createdate DESC LIMIT ?2,?3 ", nativeQuery = true)
	List<Receiptpay> findByCoupon(String userId, Integer pageNum, Integer pageSize);

}
