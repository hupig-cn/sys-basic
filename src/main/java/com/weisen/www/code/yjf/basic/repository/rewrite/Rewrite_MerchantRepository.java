package com.weisen.www.code.yjf.basic.repository.rewrite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Merchant;


@Repository
public interface Rewrite_MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query(value = "select * from merchant.merchant where userid = ?1",nativeQuery = true)
    Merchant findByUserid(String userid);

}
