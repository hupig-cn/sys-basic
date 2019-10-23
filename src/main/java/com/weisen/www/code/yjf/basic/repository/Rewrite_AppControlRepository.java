package com.weisen.www.code.yjf.basic.repository;


import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.AppControl;

/**
 * Spring Data repository for the AppControl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_AppControlRepository extends JpaRepository<AppControl, Long> {

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
