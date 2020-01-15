package com.weisen.www.code.yjf.basic.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public Rewrite_LinkuserServiceImpl(Rewrite_LinkuserRepository rewrite_LinkuserRepository,
			Rewrite_UserRepository userRepository, Rewrite_UserassetsRepository userassetsRepository) {
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.userRepository = userRepository;
		this.userassetsRepository = userassetsRepository;
	}

	@Override
	public String authentication(LinkuserDTO linkuserDTO) {
		log.debug("Request to save Linkuser : {}", linkuserDTO);
		String idCard = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		Pattern p = Pattern.compile(idCard);
		Matcher m = p.matcher(linkuserDTO.getIdcard()); // registrant_phone ==== 电话号码字段
		boolean isMatch = m.matches();
		if (!isMatch) {
			return "身份证格式不正确!请重新输入!";
		}
		if (linkuserDTO.getName().trim().equals("")) {
			return "真实姓名不能为空!";
		}
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
	 * 
	 * @param rewrite_submitMemberDTO
	 * @return
	 */
	public Result getMemberInfo(Rewrite_submitMemberDTO rewrite_submitMemberDTO) {
		if (!CheckUtils.checkPageInfo(rewrite_submitMemberDTO.getPageNum(), rewrite_submitMemberDTO.getPageSize()))
			return Result.fail("分页信息错误");
		List<Map<String, Object>> memberInfo = rewrite_LinkuserRepository.getMemberInfo(
				rewrite_submitMemberDTO.getUserName(), rewrite_submitMemberDTO.getRealName(),
				rewrite_submitMemberDTO.getPageNum() * rewrite_submitMemberDTO.getPageSize(),
				rewrite_submitMemberDTO.getPageSize());
		if (!CheckUtils.checkList(memberInfo))
			return Result.suc("数据为空");
		Integer memberInfoCount = rewrite_LinkuserRepository.getMemberInfoCount(rewrite_submitMemberDTO.getUserName(),
				rewrite_submitMemberDTO.getRealName());
		return Result.suc("获取成功", memberInfo, memberInfoCount);
	}

	// 绑定支付宝或微信账号
	@Override
	public Result bindALiPayOrWeChat(Rewrite_BIndAliWechat rewrite_BIndAliWechat) {

		Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(rewrite_BIndAliWechat.getUserid());

		if (rewrite_BIndAliWechat.getAlipay() != null) {
			linkuser.setAlipay(rewrite_BIndAliWechat.getAlipay());
		}
		if (rewrite_BIndAliWechat.getWechat() != null) {
			linkuser.setWechat(rewrite_BIndAliWechat.getWechat());
		}

		if (rewrite_BIndAliWechat.getAlipayName() != null) {
			linkuser.setAlipayname(rewrite_BIndAliWechat.getAlipayName());
		}

		if (rewrite_BIndAliWechat.getWechatName() != null) {
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

	// 删除支付宝或微信消息
	@Override
	public Result deleteALiPayorWeChat(String userid, int type) {
		Linkuser user = rewrite_LinkuserRepository.findByUserid(userid);
		if (type == 1) {
			user.setAlipay(null);
			user.setAlipayname(null);
		} else if (type == 2) {
			user.setWechat(null);
			user.setWechatname(null);
		}
		rewrite_LinkuserRepository.save(user);
		return Result.suc("成功");
	}
}
