package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Rewrite_LinkaccountRepository extends JpaRepository<Linkaccount, Long> {

    @Query(value = "select count(id) from Linkaccount where token = ?1 and accounttype = ?2")
    int countByAccountType(String token, String accountType);
    
    Linkaccount findFirstByAccounttypeAndToken(String accounttype,String token);
    
    Linkaccount findFirstByUseridAndAccounttype(String userid,String accounttype);
}