package com.weisen.www.code.yjf.basic.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Advertisement;
import com.weisen.www.code.yjf.basic.repository.Rewrite_AdvertisementRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_AdvertisementService;
import com.weisen.www.code.yjf.basic.service.dto.AdvertisementDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementOperationDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementPageDTO;
import com.weisen.www.code.yjf.basic.service.mapper.AdvertisementMapper;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.Rewrite_Constant;

@Service
@Transactional
public class Rewrite_AdvertisementServiceImpl implements Rewrite_AdvertisementService {

	private Rewrite_AdvertisementRepository rewrite_AdvertisementRepository;

	private AdvertisementMapper advertisementMapper;

	public Rewrite_AdvertisementServiceImpl(Rewrite_AdvertisementRepository rewrite_AdvertisementRepository,
			AdvertisementMapper advertisementMapper) {
		this.rewrite_AdvertisementRepository = rewrite_AdvertisementRepository;
		this.advertisementMapper = advertisementMapper;
	}

	@Override
	public Result appHomepageAdvertisement() {
		List<AdvertisementDTO> returnList = rewrite_AdvertisementRepository.findList(0, 10).stream()
				.map(advertisementMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		return Result.suc("查询成功", returnList, returnList.size());
	}

	@Override
	public Result createAdvertisement(Rewrite_AdvertisementDTO rewrite_AdvertisementDTO) {
		Result result = null;
		Integer number = rewrite_AdvertisementRepository.getCountBySort(rewrite_AdvertisementDTO.getSort());
		if (number != 0) {
			result = Result.fail("顺序序号已被占用");
		} else {
			Advertisement advertisement = new Advertisement();
			advertisement.setId(null);
			advertisement.setName(rewrite_AdvertisementDTO.getName());
			advertisement.setIntroduction(rewrite_AdvertisementDTO.getIntroduction());
			advertisement.setPictureFormat(rewrite_AdvertisementDTO.getPictureFormat());
			advertisement.setPictureLink(rewrite_AdvertisementDTO.getPictureLink());
			advertisement.setLink(rewrite_AdvertisementDTO.getLink());
			advertisement.setLinkType(rewrite_AdvertisementDTO.getLinkType());
			advertisement.setType(rewrite_AdvertisementDTO.getType());
			advertisement.setSort(rewrite_AdvertisementDTO.getSort());
			advertisement.setState(rewrite_AdvertisementDTO.getState());
			advertisement.setCreatedDate(Instant.now());
			advertisement.setLastModifiedDate(Instant.now());
			rewrite_AdvertisementRepository.save(advertisement);
			result = Result.suc("新增成功");
		}
		return result;
	}

	@Override
	public Result updateAdvertisement(Rewrite_AdvertisementDTO rewrite_AdvertisementDTO) {
		Result result = null;
		Advertisement advertisement = rewrite_AdvertisementRepository.getOne(rewrite_AdvertisementDTO.getId());
		if (null == advertisement) {
			result = Result.fail("该广告已被删除");
		}
		Integer number = rewrite_AdvertisementRepository.getCountBySort(rewrite_AdvertisementDTO.getSort());
		if (advertisement.getSort().equals(rewrite_AdvertisementDTO.getSort()) || number == 0) {
			advertisement.setName(rewrite_AdvertisementDTO.getName());
			advertisement.setIntroduction(rewrite_AdvertisementDTO.getIntroduction());
			advertisement.setPictureFormat(rewrite_AdvertisementDTO.getPictureFormat());
			advertisement.setPictureLink(rewrite_AdvertisementDTO.getPictureLink());
			advertisement.setLink(rewrite_AdvertisementDTO.getLink());
			advertisement.setLinkType(rewrite_AdvertisementDTO.getLinkType());
			advertisement.setType(rewrite_AdvertisementDTO.getType());
			advertisement.setSort(rewrite_AdvertisementDTO.getSort());
			advertisement.setState(rewrite_AdvertisementDTO.getState());
			advertisement.setLastModifiedDate(Instant.now());
			rewrite_AdvertisementRepository.saveAndFlush(advertisement);
			result = Result.suc("修改成功");
		} else {
			result = Result.fail("顺序序号已被占用");
		}
		return result;
	}

	@Override
	public Result operationAdvertisement(Rewrite_AdvertisementOperationDTO rewrite_AdvertisementOperationDTO) {
		Result result = null;
		Advertisement advertisement = rewrite_AdvertisementRepository.getOne(rewrite_AdvertisementOperationDTO.getId());
		if (null == advertisement) {
			result = Result.fail("数据不存在，ID不存在");
		} else {
			if (rewrite_AdvertisementOperationDTO.getOperation() == Rewrite_Constant.EN_ABLE) {
				advertisement.setState(Rewrite_Constant.EN_ABLE);
				rewrite_AdvertisementRepository.saveAndFlush(advertisement);
				result = Result.suc("启用成功");
			} else if (rewrite_AdvertisementOperationDTO.getOperation() == Rewrite_Constant.DIS_ABLE) {
				advertisement.setState(Rewrite_Constant.DIS_ABLE);
				rewrite_AdvertisementRepository.saveAndFlush(advertisement);
				result = Result.suc("禁用成功");
			} else if (rewrite_AdvertisementOperationDTO.getOperation() == Rewrite_Constant.DELETE) {
				rewrite_AdvertisementRepository.deleteById(rewrite_AdvertisementOperationDTO.getId());
				result = Result.suc("删除成功");
			} else {
				result = Result.fail("操作异常");
			}
		}
		return result;
	}

	@Override
	public Result batchDeleteAdvertisement(List<Long> deleteIds) {
		List<String> resultList = new ArrayList<String>();
		for (Long id : deleteIds) {
			Advertisement advertisement = rewrite_AdvertisementRepository.getOne(id);
			if(null == advertisement) {
				resultList.add("ID为"+id+"的数据不存在");
			}else {
				rewrite_AdvertisementRepository.deleteById(id);
			}
		}
		return Result.suc("操作成功", resultList);
	}

	@Override
	public Result getOneAdvertisement(Rewrite_AdvertisementOperationDTO rewrite_AdvertisementOperationDTO) {
		Result result = null;
		Advertisement advertisement = rewrite_AdvertisementRepository.getOne(rewrite_AdvertisementOperationDTO.getId());
		if (null == advertisement) {
			result = Result.fail("数据不存在，ID不存在");
		} else {
			result = Result.suc("查询成功", advertisementMapper.toDto(advertisement));
		}
		return result;
	}

	@Override
	public Result findAdvertisementListByName(Rewrite_AdvertisementPageDTO rewrite_AdvertisementPageDTO) {
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
