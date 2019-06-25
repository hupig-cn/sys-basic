package com.weisen.www.code.yjf.basic.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Advertisement;
import com.weisen.www.code.yjf.basic.domain.Feedback;
import com.weisen.www.code.yjf.basic.repository.FeedbackRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_AdvertisementRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_FeedbackService;
import com.weisen.www.code.yjf.basic.service.dto.FeedbackDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementDTO;
import com.weisen.www.code.yjf.basic.service.mapper.FeedbackMapper;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Implementation for managing {@link Feedback}.
 */
@Service
@Transactional
public class Rewrite_FeedbackServiceImpl implements Rewrite_FeedbackService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_FeedbackServiceImpl.class);

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;
    
    private Rewrite_AdvertisementRepository rewrite_AdvertisementRepository;

    public Rewrite_FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper,Rewrite_AdvertisementRepository rewrite_AdvertisementRepository) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.rewrite_AdvertisementRepository = rewrite_AdvertisementRepository;
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

}
