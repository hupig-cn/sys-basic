package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Paymethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Paymethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_PaymethodRepository extends JpaRepository<Paymethod, Long> {
    @Query(value = "select * from paymethod where os = ?1 and online =  (WHEN ?2= true then 1 else 0 and switchs = 1",nativeQuery = true)
    List<Paymethod> findPayMethodByOsAndOnline(String os, Boolean online);
    @Query(value = "select * from paymethod where switchs = 1",nativeQuery = true)
    List<Paymethod> findPaymethod();

}
