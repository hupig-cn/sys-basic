package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Paymethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Paymethod entity.
 */
@Repository
public interface Rewrite_PaymethodRepository extends JpaRepository<Paymethod, Long> {
    @Query(value = "select * from paymethod where os = :os and online = :online and switchs = 1",nativeQuery = true)
    List<Paymethod> findPayMethodByOsAndOnline(@Param("os") String os,@Param("online") Boolean online);
    @Query(value = "select * from paymethod where switchs = 1 ORDER jhi_order",nativeQuery = true)
    List<Paymethod> findPaymethod();
}
