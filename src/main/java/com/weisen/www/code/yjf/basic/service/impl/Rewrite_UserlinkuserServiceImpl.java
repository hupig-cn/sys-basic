package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkaccountRepository;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_UserLink;
import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserlinkuserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing {@link Userlinkuser}.
 */
@Service
@Transactional
public class Rewrite_UserlinkuserServiceImpl implements Rewrite_UserlinkuserService {

	private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	private final Rewrite_LinkaccountRepository rewrite_LinkaccountRepository;

	public Rewrite_UserlinkuserServiceImpl(Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository,
			Rewrite_LinkaccountRepository rewrite_LinkaccountRepository) {
		this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.rewrite_LinkaccountRepository = rewrite_LinkaccountRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public String findRecommendName(String userid) {
		Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userid);
		if (userlinkuser != null && userlinkuser.getRecommendid() != null) {
			Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userlinkuser.getRecommendid().toString());
			if (linkuser != null && linkuser.getPhone() != null) {
				return linkuser.getPhone();
			}
		}
		return "无";
	}

	@Override
	public Result findRecommendName2(String userid) {
		Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userid);
		if (userlinkuser != null && userlinkuser.getRecommendid() != null) {
			Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userlinkuser.getRecommendid().toString());
			if (linkuser != null && linkuser.getPhone() != null) {
				return Result.suc("查询成功!", linkuser.getPhone());
			}
		}
		return Result.fail("无推荐人!");
	}

	@Override
	public Boolean getMyPartner(String userid) {
		Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userid);
		if (userlinkuser != null && userlinkuser.isPartner()) {
			return true;
		} else {
			return false;
		}
	}

	// 分页查询用户的推荐人（时间 电话或token 做处理）
	@Override
	public Result findAllByRecommendAndInfo(String userid, int startPage, int pageSize) {
		List<Userlinkuser> list = rewrite_UserlinkuserRepository.findAllByRecommendidAndTime(userid,
				startPage * pageSize, pageSize);
		List<Rewrite_UserLink> listUser = new ArrayList<>();
		list.forEach(x -> {
			Rewrite_UserLink rewrite_UserLink = new Rewrite_UserLink();
			Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(x.getUserid());
			rewrite_UserLink.setTime(x.getModifierdate());
			if (linkuser.getPhone() != null && !"".equals(linkuser.getPhone())) {
				rewrite_UserLink
						.setPhoneOrToken("圆积分用户：*" + linkuser.getPhone().substring(7, linkuser.getPhone().length()));
			} else {
				Linkaccount linkaccount = rewrite_LinkaccountRepository.findFirstByUserid(x.getUserid());
				rewrite_UserLink.setPhoneOrToken(
						linkaccount.getAccounttype() + "用户：*" + linkaccount.getToken().substring(0, 4));
			}
			listUser.add(rewrite_UserLink);
		});
		return Result.suc("success", listUser);
	}
}
