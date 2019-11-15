package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.AppControl;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_OsversionRepository;
import com.weisen.www.code.yjf.basic.service.dto.AppControlDataDTO;
import com.weisen.www.code.yjf.basic.service.dto.AppControlUpdateDTO;
import com.weisen.www.code.yjf.basic.service.dto.Rewrite_AppVersionUpdateDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_OsversionService;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;

/**
 * Service Implementation for managing Osversion.
 */
@Service
@Transactional
public class Rewrite_OsversionServiceImpl implements Rewrite_OsversionService {

    private final Rewrite_OsversionRepository osversionRepository;

    public Rewrite_OsversionServiceImpl(Rewrite_OsversionRepository osversionRepository ) {
        this.osversionRepository = osversionRepository;
    }

//    public Result checkOsVersion(Rewrite_submitOsVersionDTO rewrite_submitOsVersionDTO) {
//        if(!CheckUtils.checkObj(rewrite_submitOsVersionDTO))
//            return Result.fail("校验数据错误");
//        else if (!CheckUtils.checkString(rewrite_submitOsVersionDTO.getOs()))
//            return Result.fail("设备信息错误");
//        else if(!CheckUtils.checkString(rewrite_submitOsVersionDTO.getVersion()))
//            return Result.fail("版本信息错误");
//        else {
//            // 根据设备获取最新的版本号数据
//            Map<String,String> versionByOs = osversionRepository.findVersionByOs(rewrite_submitOsVersionDTO.getOs());
//            HashMap<String,String>  version = new HashMap<>();
//            version.putAll(versionByOs);
//            ArrayList<String> loginfo = new ArrayList<>();
//            for (String s : version.get("loginfo").split("|")) {
//                loginfo.add(s);
//            }
//            version.put("loginfo",loginfo.toString());
//            return Result.suc("",version);
//        }
//    }
//
//    /**
//     * 版本控制只插入不修改
//     * 未测试，更新日志保存的格式为： 修改字体 | 修改主题 | 修改界面
//     * 版本号格式为：1.0.0 如果切割后不能转化为数字会出错
//     * @param rewrite_osversionDTO
//     * @return
//     */
//    public Result insertOsVersion(Rewrite_OsversionDTO rewrite_osversionDTO) {
//        if(!CheckUtils.checkObj(rewrite_osversionDTO))
//            return Result.fail("保存数据不能为空");
//        else if(!CheckUtils.checkString(rewrite_osversionDTO.getApkurl()))
//            return Result.fail("下载地址不能为空");
//        else if(!CheckUtils.checkString(rewrite_osversionDTO.getOs()))
//            return Result.fail("设备类型不能为空");
//        else if(!CheckUtils.checkString(rewrite_osversionDTO.getVersion()))
//            return Result.fail("版本号不能为空");
//        else if(!CheckUtils.checkIntegerByZero(rewrite_osversionDTO.getType()))
//            return Result.fail("更新类型不能为空和0");
//        else {
//            rewrite_osversionDTO.setCreatedate(DateUtils.getDateForNow());
//            Osversion osversion = osversionMapper.toEntity(rewrite_osversionDTO);
//            Osversion save = osversionRepository.save(osversion);
//            if(!CheckUtils.checkObj(save))
//                return Result.fail("数据库繁忙保存失败");
//            return Result.suc("保存成功");
//        }
//    }
//
//    public Result getOsVersionList(Rewrite_submitOsVersionListDTO rewrite_submitOsVersionListDTO) {
//        if(!CheckUtils.checkObj(rewrite_submitOsVersionListDTO))
//            return Result.fail("提交数据错误");
//        else if(!CheckUtils.checkPageInfo(rewrite_submitOsVersionListDTO.getPageNum(),rewrite_submitOsVersionListDTO.getPageSize()))
//            return Result.fail("分页信息错误");
//        else {
//            List<Osversion> versionByPage = osversionRepository.findVersionByPage(rewrite_submitOsVersionListDTO.getPageNum() * rewrite_submitOsVersionListDTO.getPageSize(), rewrite_submitOsVersionListDTO.getPageSize());
//            if(!CheckUtils.checkList(versionByPage))
//                return Result.fail("列表数据为空");
//            Integer count = osversionRepository.getCount();
//            return Result.suc("",versionByPage,count);
//        }
//    }

	/**
	 * 添加App版本
	 */
	@Override
	public Result adminAddAppControl(AppControlDataDTO appControllerDataDTO) {
		AppControl appcontrol = new AppControl();
		//判断前端是否传值
		Integer clientType = appControllerDataDTO.getClientType();
		Integer state = appControllerDataDTO.getState();
		String clientVersion = appControllerDataDTO.getClientVersion();
		String downloadUrl = appControllerDataDTO.getDownloadUrl();
		String forceUpdate = appControllerDataDTO.getForceUpdate();
		String publishTime = appControllerDataDTO.getPublishTime();
		String apkMd5 = appControllerDataDTO.getApkMd5();
		Integer apkSize = appControllerDataDTO.getApkSize();
		
		if (!(clientType == 1 || clientType == 2)) {
			return Result.fail("客户端类型不正确!");
		} else if (!(state == 1 || state == 2)) {
			return Result.fail("状态填写不正确!");
		} else if (clientVersion == null) {
			return Result.fail("版本号不能为空!");
		} else if (downloadUrl == null) {
			return Result.fail("下载地址不能为空!");
		} else if (forceUpdate == null) {
			return Result.fail("是否强制更新字段不能为空!");
		} 
		Long creater = appControllerDataDTO.getCreater();

		appcontrol.setClientType(clientType);
		appcontrol.setState(state);
		appcontrol.setClientVersion(clientVersion);
		appcontrol.setCreater(creater);
		appcontrol.setDownloadUrl(downloadUrl);
		appcontrol.setForceUpdate(forceUpdate);
		appcontrol.setUpdateLog(appControllerDataDTO.getUpdateLog());
		appcontrol.setCreateTime(TimeUtil.getDate());
		if (publishTime != null) {
			appcontrol.setPublishTime(publishTime);
		}else {
			appcontrol.setPublishTime(TimeUtil.getDate());
		}
		appcontrol.setApkMd5(apkMd5);
		appcontrol.setApkSize(apkSize);
		appcontrol.setUpdateNum(0);
		appcontrol.setLogicDelete(0);
		appcontrol.setUpdateTime(TimeUtil.getDate());
		osversionRepository.save(appcontrol);


		return Result.suc("版本"+clientVersion+"新增成功!");

	}
	//
		/**
		 * 修改App版本
		 */
		@Override
		public Result adminUpdateControl() {
			AppControl appControl = new AppControl();
//			appControl.setId(appControllerDataDTO.getId());
//			appControl.setClientType(appControllerDataDTO.getClientType());
//			appControl.setDownLoad(appControllerDataDTO.getDownLoad());
//			appControl.setImgDescribe(appControllerDataDTO.getImgDescribe());
//			appControl.setUpdateLog(appControllerDataDTO.getUpdateLog());
//			appControl.clientVersion(appAppControl.getClientVersion());
//			appControl.setQrCode(appAppControl.getQrCode());
//			appControl.setChoiceUpdate(appAppControl.getChoiceUpdate());
//			appControl.setPublishDate(appAppControl.getPublishDate());
//			appControl.setState(appAppControl.getState());
//			appControl.setRemarks(appAppControl.getRemarks());
//			appControl.setUpdateTime(TimeUtil.getDate());
//			appControl.setCreateTime(appAppControl.getCreateTime());
			osversionRepository.saveAndFlush(appControl);
			return null;
		}
	//
	//	/**
	//	 * 查询所有App（排序加算法）
	//	 */
	//	@Override
	//	public PageInfo<AppControlDTO> findAllAppControl(Rewrite_AppGetAll rewrite_AppGetAll) {
	//		String clientType = null == rewrite_AppGetAll.getClientType() ? null : rewrite_AppGetAll.getClientType();
	//
	//		Integer startNum = rewrite_AppGetAll.getStartNum();
	//		Integer pageSize = rewrite_AppGetAll.getPageSize();
	//		PageInfo<AppControlDTO> page = new PageInfo<AppControlDTO>(startNum, pageSize);
	//		int fromIndex = pageSize * startNum; // 起始索引
	//		List<AppControl> list = rewrite_AppControlRepository.findAllDesc(clientType, fromIndex, pageSize);
	//		int count = rewrite_AppControlRepository.count(clientType);
	//		page.setList(list);
	//		page.setTotalNum(count);
	//		return page;
	//	}
	//
	//	/**
	//	 * 删除App版本
	//	 */
	//	@Override
	//	public void deleteAppConTrol(Long appId) {
	//		rewrite_AppControlRepository.deleteById(appId);
	//	}
	//
	//	/**
	//	 * 查询单条数据
	//	 */
	//	@Override
	//	public AppControlDTO getOne(Long appId) {
	//		Optional<AppControl> op = rewrite_AppControlRepository.findById(appId);
	//		if (!op.isPresent()) {
	//			return null;
	//		}
	//		return appControlMapper.toDto(op.get());
	//	}

	/**
	 * App版本更新
	 */
	@Override
	public Result appVersionUpdate(AppControlUpdateDTO appControllerUpdateDTO) {
		Integer clientType = appControllerUpdateDTO.getClientType();
		String clientVersion = appControllerUpdateDTO.getClientVersion();
		
		Integer intTypeVersion = 0;
		String newTypeVersion = null;
		Rewrite_AppVersionUpdateDTO appVersionUpdateDTO = new Rewrite_AppVersionUpdateDTO();
//		//判断当前用户版本是否存在
//		List<AppControl> userClientVersion = osversionRepository.findAppControlByClientVersionAndClientType(clientVersion,clientType);
//		if (userClientVersion.isEmpty()) {
//			return Result.fail("不存在该版本，请重新下载!");
//		}
		//查找客户端类型所有版本
		List<AppControl> clientTypeData = osversionRepository.findByClientType(clientType);

		/**
		 * 判断版本是否是最新的，如果不是最新的，查看后续版本是否有要强制更新的
		 * 如果有需要强制更新的版本，直接更新到最新版本
		 * 如果有新版本，不强制更新到最新版本，提示有可用版本可更新
		 * */
		for (AppControl appControl : clientTypeData) {
			//判断版本信息是否正确
			Integer trueOrFalseState = appControl.getState();
			String forceUpdate = appControl.getForceUpdate();
			
			//获取对应类型的所有版本信息
			String allClientVersion = appControl.getClientVersion();
			//将版本装成int类型
			int newVersionData = Integer.parseInt(allClientVersion.replace(".",""));

			//判断版本int类型的大小，并找到最新版本
			if(intTypeVersion < newVersionData) {
				//最新版本不会被逻辑删除
				Integer logicDelete = appControl.getLogicDelete();
				if (logicDelete == null) {
					return Result.fail("请检查该版本是否已被删除!");
				}

				//最新版本状态为0  0：正常
				Integer state = trueOrFalseState;
				if (state.equals(0)) {
					intTypeVersion = newVersionData;
					//最新版本
					newTypeVersion =allClientVersion;
				}
			}
		}
		//找到最新版本数据
		AppControl appControllerData = osversionRepository.findByClientTypeAndClientVersion(clientType, newTypeVersion);
		if (clientVersion.equals(newTypeVersion)) {
			appVersionUpdateDTO.setUpdate("No");
			returnDTO(appVersionUpdateDTO, appControllerData);
			return Result.suc("已是最新版本!",appVersionUpdateDTO);
		}
		for (AppControl appControl : clientTypeData) {
			//获取对应类型的所有版本信息
			String allClientVersion = appControl.getClientVersion();
			//将版本装成int类型
			int newVersionData = Integer.parseInt(allClientVersion.replace(".",""));
			int nowClientVersion = Integer.parseInt(clientVersion.replace(".",""));
			//判断如果当前用户版本小于新版本,查看新版本是否强制更新
			if(newVersionData > nowClientVersion) {
				//比当前客户端新的版本信息
				AppControl newClientVersionData = osversionRepository.findByClientVersionAndClientType(allClientVersion,clientType);
				//是否强制更新
				String forceUpdate = newClientVersionData.getForceUpdate();
				//如果找到比当前版本新的版本是需要强制更新的，则强制更新到最新版本
				if (forceUpdate.equals("2")) {
					returnDTO(appVersionUpdateDTO, appControllerData);
					return Result.suc("需要强制更新版本!", appVersionUpdateDTO);
				}
			}
		}
		//如果找到比现在新的版本，新的版本都没有提示需要强制更新
		AppControl newClientVersionData = osversionRepository.findByClientVersionAndClientType(newTypeVersion,clientType);
		returnDTO(appVersionUpdateDTO, newClientVersionData);
		return Result.suc("有可更新版本!", appVersionUpdateDTO);

	}

	private void returnDTO(Rewrite_AppVersionUpdateDTO appVersionUpdateDTO, AppControl appControllerData) {
		//获取最新数据的信息，并写到DTO中
		Integer returnClientType = appControllerData.getClientType();//
		String returnClientVersion = appControllerData.getClientVersion();
		String returnPublishTime = appControllerData.getPublishTime();
		String returnDownloadUrl = appControllerData.getDownloadUrl();
		String returnUpdateLog = appControllerData.getUpdateLog();
		Integer returnUpdateNum = appControllerData.getUpdateNum();
		String returnForceUpdate = appControllerData.getForceUpdate();
		String returnapkMd5 = appControllerData.getApkMd5();
		Integer returnapkSize = appControllerData.getApkSize();
		Integer returnState = appControllerData.getState();


		appVersionUpdateDTO.setVersionCode(returnUpdateNum);
		appVersionUpdateDTO.setVersionName(returnClientVersion);
		String update = appVersionUpdateDTO.getUpdate();
		if (update==null) {
			appVersionUpdateDTO.setUpdate("Yes");
		}
		appVersionUpdateDTO.setClientType(returnClientType);
		appVersionUpdateDTO.setApkMd5(returnapkMd5);
		appVersionUpdateDTO.setApkSize(""+returnapkSize+"M");
		appVersionUpdateDTO.setDownloadUrl(returnDownloadUrl);
		appVersionUpdateDTO.setState(returnState);
		appVersionUpdateDTO.setPublishTime(returnPublishTime);
		//如果当前版本不用强制更新，客户端与当前版本中有版本需要强制更新，则将最新版本改为强制更新
		if (appVersionUpdateDTO.getUpdateStatus()!="2") {
			appVersionUpdateDTO.setUpdateStatus(returnForceUpdate);
		}
		appVersionUpdateDTO.setModifyContent(returnUpdateLog);
	}

}
