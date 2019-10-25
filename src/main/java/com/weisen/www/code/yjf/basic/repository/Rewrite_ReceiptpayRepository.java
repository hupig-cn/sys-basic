package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface Rewrite_ReceiptpayRepository extends JpaRepository<Receiptpay, Long> {

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

	// 根据时间,用户关系表查询用户的明细
	@Query(value = "select sum(bonus) from receiptpay where user= ?1 and sourcer = ?2 and createdate between firstTime = ?3 and lastTime = ?4", nativeQuery = true)
	List<Receiptpay> getReceiptpayByUseridAndSourcerAndTime(String userid, String sourcer, String firstTime,
			String lastTime);

	List<Receiptpay> findByUseridAndSourcer(String userid, String sourcer);

	// 查询用户积分支出收入
	@Transactional
	@Query(value = "select * FROM receiptpay where userid = ?1 and dealtype in (6,5) ORDER BY createdate DESC", nativeQuery = true)
	List<Receiptpay> findByUserid(String userId);

}
