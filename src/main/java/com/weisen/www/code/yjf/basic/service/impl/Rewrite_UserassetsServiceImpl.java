package com.weisen.www.code.yjf.basic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserassetsMapper;

@Service
@Transactional
public class Rewrite_UserassetsServiceImpl implements Rewrite_UserassetsService{
	
	private final Logger log = LoggerFactory.getLogger(Rewrite_UserassetsServiceImpl.class);

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final UserassetsMapper userassetsMapper;

	public Rewrite_UserassetsServiceImpl(Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			UserassetsMapper userassetsMapper) {
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.userassetsMapper = userassetsMapper;
	}
	
	//查询用户余额
	@Override
	public Rewrite_PriceDTO findUserBalance(Long userId) {
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(userId.toString());
		if(null == userassets) {
			return new Rewrite_PriceDTO("0");
		}
		return new Rewrite_PriceDTO(userassets.getBalance());
	}
	
	
}
