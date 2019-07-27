package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Withdrawal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

    @Query(value = "select * from withdrawal where userid = ?1 ORDER BY createdate DESC limit ?2,?3",nativeQuery = true)
    List<Withdrawal> getWithdrawalByAccount(String userId, Integer pageNum, Integer pageSize);

    @Query(value = "select count(*) from withdrawal where (:userId IS NULL OR userid = :userId)",nativeQuery = true)
    Integer getCountByAccount(@Param("userId") String userId);

    @Query(value = "update withdrawal set withdrawaltype = 1,modifier = ?3,other = ?2,modifiernum = modifiernum + 1 where id = ?1 and withdrawaltype = 2",nativeQuery = true)
    @Modifying
    Integer auditWithdrawal(Long id, String other, String Modifier);

    @Query(value = "select * from withdrawal ORDER BY createdate DESC limit ?1,?2",nativeQuery = true)
    List<Withdrawal> getWithdrawals(Integer pageNum, Integer pageSize);

    // 根据提现状态获取
    List<Withdrawal> findAllByUseridAndWithdrawaltype(String userid,String withdrawaltype);
}
