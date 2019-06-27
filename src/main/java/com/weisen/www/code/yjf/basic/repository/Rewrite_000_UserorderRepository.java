package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Rewrite_000_UserorderRepository extends JpaRepository<Userorder, Long> {

    @Query(value = "from Userorder where ordercode = ?1")
    Userorder findByOrdercode(String ordercode);
}
