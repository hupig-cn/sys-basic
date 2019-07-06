package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Coderecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Coderecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_CoderecordRepository extends JpaRepository<Coderecord, Long> {

    @Query(value = "select * from coderecord where phone = ?1 and type = ?2",nativeQuery = true)
    Coderecord findCodeByPhoneAndAct(String phone, String act);

    @Query(value = "update coderecord set code = ?2 ,update_date = ?4,num = 1 where id = ?1 and update_date = ?3", nativeQuery = true)
    @Modifying
    Integer updateCodeByTime(Long id, String code, String updateDate, String dateForNow);
    @Query(value = "update coderecord set code = ?3 ,num = num +1,update_date = ?4 where id = ?1 and num = ?2",nativeQuery = true)
    @Modifying
    Integer updateCodeByNumber(Long id, Integer num, String code, String time);
    @Query(value = "select * from coderecord where phone = ?1 and type = ?2 and code = ?3 and TIMESTAMPDIFF(MINUTE,update_date,NOW()) < 3",nativeQuery = true)
    Coderecord findCodeByPhoneAndActAndCode(String phone, String act, String code);
}

