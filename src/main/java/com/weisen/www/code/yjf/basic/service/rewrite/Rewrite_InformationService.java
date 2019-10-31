package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitInformationDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_InformationDetailsDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_InformationService {
	// 保存消息
	Result insertInformation(Rewrite_submitInformationDTO rewrite_submitInformationDTO);

	// 获取消息详情
	Result readInformation(Long id, String userid);

	// 消息全部已读
	Result readAllInformation(String userId);

	// 消息批量删除
	Result deleteInformations(Long[] ids, String userId);

	// 获取账号消息列表
	Result getInformations(Rewrite_InformationDetailsDTO details);

	// 内部服务保存消息
	String insertInformation(InformationDTO informationDTO);

	// 删除用户全部消息
	Result deleteUserInformations(String userId);
}
