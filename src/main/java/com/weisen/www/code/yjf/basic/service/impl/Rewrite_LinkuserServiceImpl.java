package com.weisen.www.code.yjf.basic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_LinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;
import com.weisen.www.code.yjf.basic.service.mapper.LinkuserMapper;

/**
 * Service Implementation for managing {@link Linkuser}.
 */
@Service
@Transactional
public class Rewrite_LinkuserServiceImpl implements Rewrite_LinkuserService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_LinkuserServiceImpl.class);

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	private final LinkuserMapper linkuserMapper;

	public Rewrite_LinkuserServiceImpl(Rewrite_LinkuserRepository rewrite_LinkuserRepository,
			LinkuserMapper linkuserMapper) {
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.linkuserMapper = linkuserMapper;
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
			if (null != linkuser.getIdcard() && "".equals(linkuser.getIdcard()) && linkuser.getIdcard().length() == 18 && null != linkuser.getName()) {
				return "已认证";
			}
		}
		return "未认证";
	}
}
