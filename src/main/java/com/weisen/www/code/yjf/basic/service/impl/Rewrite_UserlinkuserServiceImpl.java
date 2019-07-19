package com.weisen.www.code.yjf.basic.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserlinkuserService;

/**
 * Service Implementation for managing {@link Userlinkuser}.
 */
@Service
@Transactional
public class Rewrite_UserlinkuserServiceImpl implements Rewrite_UserlinkuserService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_UserlinkuserServiceImpl.class);

	private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	public Rewrite_UserlinkuserServiceImpl(Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository) {
		this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public String findRecommendName(String userid) {
		Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userid);
		if (userlinkuser != null && userlinkuser.getRecommendid() != null) {
			Optional<Linkuser> linkuser = rewrite_LinkuserRepository
					.findByUserid(userlinkuser.getRecommendid().toString());
			if (linkuser != null && linkuser.get().getPhone() != null) {
				return linkuser.get().getPhone();
			}
		}
		return "无";
	}
}
