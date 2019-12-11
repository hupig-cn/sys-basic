package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.Deviceinfobean;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.DeviceinfobeanRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_rewriteoldportService;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_DeviceinfobeanDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.shenfenzhenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_rewriteoldportServiceImpl implements Rewrite_rewriteoldportService {

	private final Rewrite_LinkuserRepository rewrite_linkuserRepository;

	private final DeviceinfobeanRepository rewrite_DeviceinfobeanRepository;

	public Rewrite_rewriteoldportServiceImpl(Rewrite_LinkuserRepository rewrite_linkuserRepository,
			DeviceinfobeanRepository rewrite_DeviceinfobeanRepository) {
		this.rewrite_linkuserRepository = rewrite_linkuserRepository;
		this.rewrite_DeviceinfobeanRepository = rewrite_DeviceinfobeanRepository;
	}

	@Override
	public Result verificationIdCard(String userid, String idcard, String name) {
		Linkuser byIdcard = rewrite_linkuserRepository.findByIdcard(idcard);
		if (byIdcard != null) {
			return Result.fail("该身份证已被注册");
		}
		Linkuser byUserid = rewrite_linkuserRepository.findByUserid(userid);
		if (byUserid == null) {
			return Result.fail("没有该用户");
		} else if (byUserid.getIdcard() != null) {
			return Result.fail("已验证，请勿重复验证");
		}
		boolean idNumber = shenfenzhenUtil.isIDNumber(idcard);
		boolean b = shenfenzhenUtil.ClearName(name);// 百家姓
		boolean b1 = shenfenzhenUtil.checkNameChese(name);// 判断是否全部为中文
		int length = name.toCharArray().length;
		if (length >= 2 && length <= 4) {
			if (!idNumber) {
				return Result.fail("身份证输入错误");
			} else if (!b) {
				return Result.fail("输入特殊姓氏，请联系客服验证个人信息");
			} else if (!b1) {
				return Result.fail("请输入全部汉字");
			} else {
				Long id = byUserid.getId();
				byUserid.setId(id);
				byUserid.setIdcard(idcard);
				byUserid.setName(name);
				rewrite_linkuserRepository.save(byUserid);
				return Result.fail("身份证输入错误");
			}
		} else {
			return Result.fail("名字太长或者太短，请联系客服验证个人信息");
		}

	}

	@Override
	public Result selectVerification(String userid) {
		Linkuser byUserid = rewrite_linkuserRepository.findByUserid(userid);
		if (byUserid == null) {
			return Result.fail("没有该用户");
		} else if (byUserid.getIdcard() != null) {
			return Result.suc("已验证");
		} else {
			return Result.fail("未验证");
		}
	}

	/**
	 * 保存用户手机型号信息 LuoJinShui
	 */
	@Override
	public Result saveUserMobileInformation(Rewrite_DeviceinfobeanDTO rewrite_DeviceinfobeanDTO) {
		Deviceinfobean deviceinfobean = new Deviceinfobean();
		// 安卓版本
		deviceinfobean.setPlatformandroid(rewrite_DeviceinfobeanDTO.getPlatformandroid());
		// IOS版本
		deviceinfobean.setPlatformios(rewrite_DeviceinfobeanDTO.getPlatformios());
		// 用户ID
		deviceinfobean.setUserid(rewrite_DeviceinfobeanDTO.getUserid());
		// 平台
		deviceinfobean.setPlatformname(rewrite_DeviceinfobeanDTO.getPlatformname());
		// 制造商
		deviceinfobean.setDevicemanufacturer(rewrite_DeviceinfobeanDTO.getDevicemanufacturer());
		// 型号
		deviceinfobean.setDevicemodel(rewrite_DeviceinfobeanDTO.getDevicemodel());
		// CPU架构
		deviceinfobean.setDeviceabis(rewrite_DeviceinfobeanDTO.getDeviceabis());
		// SDK版本名
		deviceinfobean.setSdkversionname(rewrite_DeviceinfobeanDTO.getSdkversionname());
		// SDK版本号
		deviceinfobean.setSdkversioncode(rewrite_DeviceinfobeanDTO.getSdkversioncode());
		// 系统名
		deviceinfobean.setRomname(rewrite_DeviceinfobeanDTO.getRomname());
		// 系统版本
		deviceinfobean.setRomversion(rewrite_DeviceinfobeanDTO.getRomversion());
		// 屏幕宽度
		deviceinfobean.setScreenwidth(rewrite_DeviceinfobeanDTO.getScreenwidth());
		// 屏幕高度
		deviceinfobean.setScreenheight(rewrite_DeviceinfobeanDTO.getScreenheight());
		// APP界面宽度
		deviceinfobean.setAppscreenwidth(rewrite_DeviceinfobeanDTO.getAppscreenwidth());
		// APP界面高度
		deviceinfobean.setAppscreenheight(rewrite_DeviceinfobeanDTO.getAppscreenheight());
		// 屏幕分辨率
		deviceinfobean.setScreendensitydpi(rewrite_DeviceinfobeanDTO.getScreendensitydpi());
		// 屏幕大小
		deviceinfobean.setScreendensity(rewrite_DeviceinfobeanDTO.getScreendensity());
		// APP版本名
		deviceinfobean.setAppversionname(rewrite_DeviceinfobeanDTO.getAppversionname());
		// APP版本号
		deviceinfobean.setAppversioncode(rewrite_DeviceinfobeanDTO.getAppversioncode());
		// 是否Root
		deviceinfobean.setIsroot(rewrite_DeviceinfobeanDTO.getIsroot());
		rewrite_DeviceinfobeanRepository.save(deviceinfobean);
		return Result.suc("保存成功!", deviceinfobean);
	}
}
