package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_FeedbackDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Feedback}.
 */
public interface Rewrite_FeedbackService {
	
	Result createFeedback(Rewrite_FeedbackDTO rewrite_FeedbackDTO);

}
