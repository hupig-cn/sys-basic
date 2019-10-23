package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.AppControl;
import com.weisen.www.code.yjf.basic.domain.Osversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Spring Data  repository for the Osversion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_OsversionRepository extends JpaRepository<AppControl, Long> {
//    @Query(value = "SELECT * FROM osversion WHERE os = ?1 ORDER BY createdate DESC LIMIT 1",nativeQuery = true)
//    Map<String,String> findVersionByOs(String os);
//    @Query(value = "SELECT * FROM osversion ORDER BY createdate DESC LIMIT ?1,?2",nativeQuery = true)
//    List<Osversion> findVersionByPage(int pageNum, Integer pageSize);
//    @Query(value = "SELECT count(*) FROM osversion ",nativeQuery = true)
//    Integer getCount();
//    
	
	// 模块:App版本更新,根据客户端类型和客户端版本查找数据
	AppControl findByClientTypeAndClientVersion(Integer clientType,String clientVersion);
	
	// 模块:App版本更新,根据客户端类型查找数据
	List<AppControl> findByClientType(Integer clientType);
	
	// 模块:App版本更新,根据客户端版本和客户端类型查找数据
	@Query(value = "SELECT * from app_control WHERE client_version =?1 and client_type=?2", nativeQuery = true)
	List<AppControl> findAppControlByClientVersionAndClientType(String clientVersion,Integer clientType);
	
	// 模块:App版本更新,根据客户端版本和客户端类型查找数据
	AppControl findByClientVersionAndClientType(String clientVersion , Integer clientType);

}
