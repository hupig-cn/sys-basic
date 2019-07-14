package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Area;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_AreaRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_AreaService;

@Service
@Transactional
public class Rewrite_AreaServiceImpl implements Rewrite_AreaService {

    private final Rewrite_AreaRepository rewrite_AreaRepository;

    public Rewrite_AreaServiceImpl(Rewrite_AreaRepository rewrite_AreaRepository) {
        this.rewrite_AreaRepository = rewrite_AreaRepository;
    }

    public List<Area> findNextAreaByPname(String pname) {
    	List<Area> area = rewrite_AreaRepository.findByPnameOrderById(pname);
    	return !area.isEmpty()?area:null;
    }
}
