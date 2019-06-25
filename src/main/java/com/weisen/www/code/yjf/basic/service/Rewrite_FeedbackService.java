package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.FeedbackDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisementDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Feedback}.
 */
public interface Rewrite_FeedbackService {
	
	Result createAdvertisement(Rewrite_AdvertisementDTO rewrite_AdvertisementDTO);

}
