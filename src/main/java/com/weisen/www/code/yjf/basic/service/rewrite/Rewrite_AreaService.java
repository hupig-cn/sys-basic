package com.weisen.www.code.yjf.basic.service.rewrite;

import java.util.List;

import com.weisen.www.code.yjf.basic.domain.Area;

public interface Rewrite_AreaService {
	List<Area> findNextAreaByPname(String pname);
}
