package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_099_重写之前接口;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_rewriteoldportService;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_DeviceinfobeanDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 优惠活动接口
 *
 * @author sxx
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "099_重写接口")
public class Rewrite_rewriteoldportResouce {

	private final Logger logger = LoggerFactory.getLogger(Rewrite_rewriteoldportResouce.class);

	private final Rewrite_rewriteoldportService rewrite_rewriteoldportService;

	public Rewrite_rewriteoldportResouce(Rewrite_rewriteoldportService rewrite_rewriteoldportService) {
		this.rewrite_rewriteoldportService = rewrite_rewriteoldportService;
	}

	// Shui
	@PostMapping("/user/verificationIdCard")
	@ApiOperation("验证身份证")
	public ResponseEntity<Result> verificationIdCard(String userid, String idcard, String name) {
		Result result = rewrite_rewriteoldportService.verificationIdCard(userid, idcard, name);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/verificationIdCard", userid + "，" + idcard, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/user/selectVerification")
	@ApiOperation("查询是否验证通过")
	public ResponseEntity<Result> selectVerification(String userid) {
		Result result = rewrite_rewriteoldportService.selectVerification(userid);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/selectVerification", userid, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	/**
	 * 保存用户手机型号信息
	 * 
	 * @author LuoJinShui
	 * @param rewrite_DeviceinfobeanDTO
	 * @return
	 */
	@PostMapping("/user/saveUserMobileInformation")
	@ApiOperation("保存用户手机型号信息")
	public ResponseEntity<Result> saveUserMobileInformation(
			@RequestBody Rewrite_DeviceinfobeanDTO rewrite_DeviceinfobeanDTO) {
		Result result = rewrite_rewriteoldportService.saveUserMobileInformation(rewrite_DeviceinfobeanDTO);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/saveUserMobileInformation", rewrite_DeviceinfobeanDTO, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
}
