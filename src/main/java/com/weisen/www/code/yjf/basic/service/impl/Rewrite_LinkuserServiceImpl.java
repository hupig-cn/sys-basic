package com.weisen.www.code.yjf.basic.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_LinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_BIndAliWechat;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_findByUserAccountOrSomethingDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitMemberDTO;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Implementation for managing {@link Linkuser}.
 */
@Service
@Transactional
public class Rewrite_LinkuserServiceImpl implements Rewrite_LinkuserService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_LinkuserServiceImpl.class);

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	private final Rewrite_UserRepository userRepository;

	private final Rewrite_UserassetsRepository userassetsRepository;

	public Rewrite_LinkuserServiceImpl(Rewrite_LinkuserRepository rewrite_LinkuserRepository ,
			Rewrite_UserRepository userRepository,
			Rewrite_UserassetsRepository userassetsRepository) {
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.userRepository = userRepository;
		this.userassetsRepository = userassetsRepository;
	}

	@Override
	public String authentication(LinkuserDTO linkuserDTO) {
		log.debug("Request to save Linkuser : {}", linkuserDTO);
		Linkuser linkuserI = rewrite_LinkuserRepository.findByIdcard(linkuserDTO.getIdcard());
		if (linkuserI != null)
			return "该身份证号码已被认证。";
		Linkuser linkuserII = rewrite_LinkuserRepository.findByUserid(linkuserDTO.getUserid());
		if (linkuserII == null)
			return "认证失败。";
		linkuserII.setName(linkuserDTO.getName());
		linkuserII.setIdcard(linkuserDTO.getIdcard());
		rewrite_LinkuserRepository.save(linkuserII);
		return "认证成功";
	}

	@Override
	@Transactional(readOnly = true)
	public Linkuser findByUserid(String userid) {
		log.debug("Request to get Linkuser : {}", userid);
		return rewrite_LinkuserRepository.findByUserid(userid);
	}

	@Override
	public String queryRealName(String userid) {
		Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userid);
		if (null != linkuser) {
			if (null != linkuser.getIdcard() && null != linkuser.getName() && linkuser.getIdcard().length() == 18) {
				return "已认证";
			}
		}
		return "未认证";
	}

	/**
	 * 获取会员信息
	 * @param rewrite_submitMemberDTO
	 * @return
	 */
	public Result getMemberInfo(Rewrite_submitMemberDTO rewrite_submitMemberDTO) {
		if(!CheckUtils.checkPageInfo(rewrite_submitMemberDTO.getPageNum(),rewrite_submitMemberDTO.getPageSize()))
			return Result.fail("分页信息错误");
		//		else {
		//			List<Linkuser> linkuserList = rewrite_LinkuserRepository.findByPage(rewrite_submitMemberDTO.getPageNum() * rewrite_submitMemberDTO.getPageSize(), rewrite_submitMemberDTO.getPageSize());
		//			List<Rewrite_findByUserAccountOrSomethingDTO> findByUserAccountOrSomethingDTO = new ArrayList<>();
		//			for (Linkuser linkuser : linkuserList) {
		//				Rewrite_findByUserAccountOrSomethingDTO findByUserAccountOrSomething = new Rewrite_findByUserAccountOrSomethingDTO();
		//				String userid = linkuser.getUserid();
		//				User jhiUser = userRepository.findJhiUserById(Long.parseLong(userid));
		//
		//				String firstName= jhiUser.getFirstName();
		//				Instant lastModifiedDate = jhiUser.getLastModifiedDate();
		//				String name = linkuser.getName();
		//				String realName =  null;
		//				if (name== null ||name.equals("")) {
		//					realName = "已实名";
		//				}else {
		//					realName = "未实名";
		//				}
		//				String password = linkuser.getPaypassword();
		//				String paypassword =  null;
		//				if (password==null || password.equals("")) {
		//					paypassword = "未设置";
		//				} else {
		//					paypassword = "已设置";
		//				}
		//				String createdate = linkuser.getCreatedate();
		//				Userassets userassets = userassetsRepository.findByUserid(userid);
		//				String balance = userassets.getBalance();
		//				findByUserAccountOrSomething.setBalance(new BigDecimal(balance));
		//				
		//			}
		//			List<Linkuser> findAll = rewrite_LinkuserRepository.findAll();
		//			int size = findAll.size();
		//			

		//		}

		List<Map<String, Object>> memberInfo = rewrite_LinkuserRepository.getMemberInfo(rewrite_submitMemberDTO.getUserName(), rewrite_submitMemberDTO.getRealName(),
				rewrite_submitMemberDTO.getPageNum() * rewrite_submitMemberDTO.getPageSize(), rewrite_submitMemberDTO.getPageSize());
		if(!CheckUtils.checkList(memberInfo))
			return Result.suc("数据为空");
		Integer memberInfoCount = rewrite_LinkuserRepository.getMemberInfoCount(rewrite_submitMemberDTO.getUserName(), rewrite_submitMemberDTO.getRealName());
		return Result.suc("获取成功",memberInfo,memberInfoCount);
//		return null;

	}

	//绑定支付宝或微信账号
	@Override
	public Result bindALiPayOrWeChat(Rewrite_BIndAliWechat rewrite_BIndAliWechat) {

		Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(rewrite_BIndAliWechat.getUserid());

		if(rewrite_BIndAliWechat.getAlipay() != null){
			linkuser.setAlipay(rewrite_BIndAliWechat.getAlipay());
		}
		if(rewrite_BIndAliWechat.getWechat() != null){
			linkuser.setWechat(rewrite_BIndAliWechat.getWechat());
		}

		if(rewrite_BIndAliWechat.getAlipayName() != null){
			linkuser.setAlipayname(rewrite_BIndAliWechat.getAlipayName());
		}

		if(rewrite_BIndAliWechat.getWechatName() != null){
			linkuser.setWechatname(rewrite_BIndAliWechat.getWechatName());
		}
		rewrite_LinkuserRepository.saveAndFlush(linkuser);

		return Result.suc("成功");
	}

	// 会员列表(上面已有)
	@Override
	public Result findAllUserList(int pageIndex, int pageSize) {



		return null;
	}
}
