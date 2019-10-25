package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Rewrite_MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query(value = "select * from login.jhi_user where id = ?1", nativeQuery = true)
    Merchant findFirstByUserid(String userid);
}
