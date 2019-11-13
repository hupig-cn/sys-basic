package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 11:32
 */
@Repository
public interface Rewrite_ShopRepository extends JpaRepository<Shopping, Long> {

    @Query(value = "select * from shopmall.Shopping where id = ?1 ",nativeQuery = true)
    Shopping findShoppingById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    //DELETE FROM 表名称 WHERE 列名称 = 值
    @Query(value = "delete from shopmall.Shopping where id = ?1 ",nativeQuery = true)
    int deleteShoppingById(Long id);

}
