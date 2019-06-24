package com.weisen.www.code.yjf.basic.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.repository.Rewrite_AdvertisementRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_NearbyMerchantsService;
import com.weisen.www.code.yjf.basic.service.dto.AdvertisementDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementPageDTO;
import com.weisen.www.code.yjf.basic.service.mapper.AdvertisementMapper;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_NearbyMerchantsServiceImpl implements Rewrite_NearbyMerchantsService {

	private Rewrite_AdvertisementRepository rewrite_AdvertisementRepository;

	private AdvertisementMapper advertisementMapper;

	public Rewrite_NearbyMerchantsServiceImpl(Rewrite_AdvertisementRepository rewrite_AdvertisementRepository,
			AdvertisementMapper advertisementMapper) {
		this.rewrite_AdvertisementRepository = rewrite_AdvertisementRepository;
		this.advertisementMapper = advertisementMapper;
	}

	@Override
	public Result findList(Rewrite_AdvertisementPageDTO rewrite_AdvertisementPageDTO) {
		String name = rewrite_AdvertisementPageDTO.getName();
		Integer type = rewrite_AdvertisementPageDTO.getType();
		if (null == name || "" == name.trim()) {
			name = null;
		}
		List<AdvertisementDTO> returnList = rewrite_AdvertisementRepository.findListByName(name,type,
						rewrite_AdvertisementPageDTO.getPageNum() * rewrite_AdvertisementPageDTO.getPageSize(),
						rewrite_AdvertisementPageDTO.getPageSize())
				.stream().map(advertisementMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		Integer totalElements = rewrite_AdvertisementRepository.getCountByName(rewrite_AdvertisementPageDTO.getName(),type);
		return Result.suc("查询成功", returnList, totalElements);
	}

}
