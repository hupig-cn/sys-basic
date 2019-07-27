package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userbankcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_UserbankcardRepository extends JpaRepository<Userbankcard, Long> {

    // 查询用户的银行卡
    List<Userbankcard> findAllByUserid(String userid);
}
