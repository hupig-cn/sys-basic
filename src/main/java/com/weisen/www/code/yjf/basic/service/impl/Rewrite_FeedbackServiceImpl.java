package com.weisen.www.code.yjf.basic.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Feedback;
import com.weisen.www.code.yjf.basic.repository.Rewrite_FeedbackRepository;
import com.weisen.www.code.yjf.basic.security.SecurityUtils;
import com.weisen.www.code.yjf.basic.service.Rewrite_FeedbackService;
import com.weisen.www.code.yjf.basic.service.dto.FeedbackDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_FeedbackDTO;
import com.weisen.www.code.yjf.basic.service.mapper.FeedbackMapper;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;

/**
 * Service Implementation for managing {@link Feedback}.
 */
@Service
@Transactional
public class Rewrite_FeedbackServiceImpl implements Rewrite_FeedbackService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_FeedbackServiceImpl.class);

    private final Rewrite_FeedbackRepository rewrite_FeedbackRepository;

    private final FeedbackMapper feedbackMapper;
    
    public Rewrite_FeedbackServiceImpl(Rewrite_FeedbackRepository rewrite_FeedbackRepository, FeedbackMapper feedbackMapper) {
        this.rewrite_FeedbackRepository = rewrite_FeedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }
    
    @Override
	public Result createFeedback(Rewrite_FeedbackDTO rewrite_FeedbackDTO) {
		Feedback feedback = new Feedback();
		feedback.setId(null);
		feedback.setName(rewrite_FeedbackDTO.getName());
		feedback.setFeedbackdate(TimeUtil.getDate());
		feedback.setTitle(rewrite_FeedbackDTO.getTitle());
		feedback.setContent(rewrite_FeedbackDTO.getContent());
		feedback.setState(rewrite_FeedbackDTO.getState());
		feedback.setImageurl(rewrite_FeedbackDTO.getImageurl());
		feedback.setCreatedate(TimeUtil.getDate());
		feedback.setModifierdate(TimeUtil.getDate());
		feedback.setModifiernum(rewrite_FeedbackDTO.getModifiernum());
		feedback.setLogicdelete(false);
		feedback.setOther(rewrite_FeedbackDTO.getOther());
		Optional<String> op = SecurityUtils.getCurrentUserLogin();
		if(op.isPresent()) {
			String userName = op.get();
			feedback.setCreator(userName);
			feedback.setModifier(userName);
		}
		rewrite_FeedbackRepository.save(feedback);
		return Result.suc("新增成功");
	}
    
    @Override
	public Result findFeedbackList() {
		List<FeedbackDTO> returnList = rewrite_FeedbackRepository.findList(0,10000).stream()
				.map(feedbackMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		return Result.suc("查询成功", returnList, returnList.size());
	}

}
