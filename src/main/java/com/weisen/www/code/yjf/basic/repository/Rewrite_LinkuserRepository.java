package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Spring Data repository for the Linkuser entity.
 */
@Repository
public interface Rewrite_LinkuserRepository extends JpaRepository<Linkuser, Long> {

	Linkuser findByUserid(String userid);

	Linkuser findByIdcard(String idcard);

	Linkuser findByPhone(String phone);
    @Query(value = "select l.userid as userid ,l.phone as phone ,l.name as name , a.balance as balance,a.integral as integral , (case when l.paypassword IS NOT NULL THEN '已设置' ELSE '未设置' END ) as password," +
        "(CASE WHEN l.name IS NOT NULL THEN '已实名' ELSE '未实名' END ) as realname,l.createdate as createtime ,ulu.recommendid as recommendid from linkuser l RIGHT JOIN userassets a ON l.userid = a.userid JOIN userlinkuser ulu ON ulu.userid = l.userid " +
        "where (:userName IS NULL OR :userName = phone) AND (CASE WHEN :realName IS NULL THEN 1 = 1  WHEN :realName = 1 THEN l.name IS NOT NULL  ELSE  l.name IS NULL END ) ORDER BY l.createdate DESC LIMIT :pageNum,:pageSize ",nativeQuery = true)
    List<Map<String,Object>> getMemberInfo(@Param("userName") String userName,@Param("realName") Integer realName,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);
    @Query(value = "select count(*) from linkuser l RIGHT JOIN userassets a ON l.userid = a.userid JOIN userlinkuser ulu ON ulu.userid = l.userid " +
        "where (:userName IS NULL OR :userName = phone) AND (CASE WHEN :realName IS NULL THEN 1 = 1  WHEN :realName = 1 THEN l.name IS NOT NULL  ELSE  l.name IS NULL END ) ORDER BY l.createdate DESC",nativeQuery = true)
    Integer getMemberInfoCount(@Param("userName") String userName,@Param("realName") Integer realName);
}
