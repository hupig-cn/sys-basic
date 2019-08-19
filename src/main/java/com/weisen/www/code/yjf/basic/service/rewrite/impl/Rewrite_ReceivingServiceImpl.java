package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Receiving;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_ReceivingRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_ReceivingService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ReceivingDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_ReceivingMapper;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_ReceivingServiceImpl implements Rewrite_ReceivingService {

    private final Rewrite_ReceivingMapper rewrite_receivingMapper;

    private final Rewrite_ReceivingRepository rewrite_receivingRepository;

    public Rewrite_ReceivingServiceImpl(Rewrite_ReceivingMapper rewrite_receivingMapper, Rewrite_ReceivingRepository rewrite_receivingRepository ) {
        this.rewrite_receivingMapper = rewrite_receivingMapper;
        this.rewrite_receivingRepository = rewrite_receivingRepository;
    }


    /**
     * 根据用户id去查询用户的所有收货地址
     *
     * @param id
     * @return
     */
    public Result getUserAddress(Long id) {
        if (!CheckUtils.checkLongByZero(id))
            return Result.fail("用户标识为空");
        else {
            List<Receiving> receivingByUserId = rewrite_receivingRepository.getReceivingByUserId(id);
            List<Rewrite_ReceivingDTO> rewrite_receivingDTOS = rewrite_receivingMapper.toDto(receivingByUserId);
//            rewrite_receivingDTOS.forEach(receiving -> {
//                    Optional<Area> byId = rewrite_areaRepository.findById(Long.valueOf(receiving.getAreaid()));
//                    Area area = byId.get();
//                    receiving.setProvince(area.getGname());
//                    receiving.setProvinceId(area.getGid());
//                    if (area.getGid() == area.getPid()) {
//                        receiving.setCity(null);
//                        receiving.setCityId(null);
//                    } else {
//                        receiving.setCity(area.getGname());
//                        receiving.setCityId(area.getGid());
//                    }
//                    receiving.setAreaName(area.getName());
//                }
//            );
            return Result.suc("获取成功", rewrite_receivingDTOS);
        }
    }

    public Result insertUserAddress(Rewrite_ReceivingDTO rewrite_receivingDTO) {
        if (!CheckUtils.checkObj(rewrite_receivingDTO))
            return Result.fail("收货地址数据为空");
        else if (!CheckUtils.checkString(rewrite_receivingDTO.getAddress()))
            return Result.fail("详细地址不能为空");
        else if (!CheckUtils.checkString(rewrite_receivingDTO.getMobile()))
            return Result.fail("收货人手机号码不能为空");
        else if (!CheckUtils.checkString(rewrite_receivingDTO.getConsignee()))
            return Result.fail("收货人不能为空");
        else if (!CheckUtils.checkLongByZero(rewrite_receivingDTO.getUserid()))
            return Result.fail("用户标识不能为空");
//        else if (!CheckUtils.checkString(rewrite_receivingDTO.getAreaid()))
//            return Result.fail("地区信息不能为空");
        else {
//            Optional<Area> byId = rewrite_areaRepository.findById(Long.valueOf(rewrite_receivingDTO.getAreaid()));
//            Area area = byId.get();
//            if (!CheckUtils.checkObj(area))
//                return Result.fail("地区信息错误");
            //转换成相应的实体类在保存到数据库
            //保存创建时间
            Receiving receiving = rewrite_receivingMapper.toEntity(rewrite_receivingDTO);
            Receiving UserIdAndDefault = rewrite_receivingRepository.findByUserIdAndDefault(Long.valueOf(rewrite_receivingDTO.getUserid()));
            //判断当前用户是否存在默认地址
            //不存在则设置成默认地址
            if (!CheckUtils.checkObj(UserIdAndDefault))
                receiving.setIsdefault(true);
                //存在并且提交的数据为设置成默认地址则修改
            else {
                if (rewrite_receivingDTO.getIsdefault()) {
                    UserIdAndDefault.setIsdefault(false);
                    rewrite_receivingRepository.saveAndFlush(UserIdAndDefault);
                }
            }
            receiving.setCreatetime(DateUtils.getDateForNow());
            Receiving save = rewrite_receivingRepository.save(receiving);
            if (!CheckUtils.checkObj(save))
                return Result.fail("网络繁忙请稍后重试");
            return Result.suc("保存成功");
        }
    }

    /**
     * 修改收货信息
     *
     * @param rewrite_receivingDTO
     * @return
     */
    public Result updateUserAddress(Rewrite_ReceivingDTO rewrite_receivingDTO) {
        if (!CheckUtils.checkObj(rewrite_receivingDTO))
            return Result.fail("收货信息不能为空");
        else if (!CheckUtils.checkLongByZero(Long.valueOf(rewrite_receivingDTO.getUserid())))
            return Result.fail("用户标识错误");
        else if (!CheckUtils.checkLongByZero(rewrite_receivingDTO.getId()))
            return Result.fail("修改的对象错误");
        else {
            Receiving receiving = rewrite_receivingRepository.findByUserIdAndId(rewrite_receivingDTO.getUserid(), rewrite_receivingDTO.getId());
            if (!CheckUtils.checkString(rewrite_receivingDTO.getMobile()) && !CheckUtils.checkString(rewrite_receivingDTO.getConsignee())
                && !CheckUtils.checkString(rewrite_receivingDTO.getAreaid()) && !CheckUtils.checkString(rewrite_receivingDTO.getAddress()))
                return Result.fail("没有需要修改的信息");
            if (CheckUtils.checkString(rewrite_receivingDTO.getMobile()))
                receiving.setMobile(rewrite_receivingDTO.getMobile());
            if (CheckUtils.checkString(rewrite_receivingDTO.getConsignee()))
                receiving.setConsignee(rewrite_receivingDTO.getConsignee());
            if (CheckUtils.checkString(rewrite_receivingDTO.getAreaid()))
                receiving.setAreaid(rewrite_receivingDTO.getAreaid());
            if (CheckUtils.checkString(rewrite_receivingDTO.getAddress()))
                receiving.setAddress(rewrite_receivingDTO.getAddress());
            if (receiving.isIsdefault() != rewrite_receivingDTO.getIsdefault()) {
                if (rewrite_receivingDTO.isIsdefault()) {
                    Receiving byUserIdAndDefault = rewrite_receivingRepository.findByUserIdAndDefault(Long.valueOf(receiving.getUserid()));
                    System.out.println(byUserIdAndDefault);
                if (null != byUserIdAndDefault && byUserIdAndDefault.getId() != receiving.getId()) {
                    byUserIdAndDefault.setIsdefault(false);
                    rewrite_receivingRepository.saveAndFlush(byUserIdAndDefault);
                }
                }
                receiving.setIsdefault(rewrite_receivingDTO.getIsdefault());
            }
            Receiving save = rewrite_receivingRepository.saveAndFlush(receiving);
            if (!CheckUtils.checkObj(save))
                return Result.fail("网络繁忙请稍后重试");
            return Result.suc("修改成功");
        }
    }

    /**
     * 获取默认地址
     *
     * @param id
     * @return
     */
    public Result getDefaultAddress(Long UserId) {
        if (!CheckUtils.checkLongByZero(UserId))
            return Result.fail("用户标识错误");
        else {
//            Receiving receiving = rewrite_receivingRepository.findByUserIdAndDefault(UserId);
            Receiving byUserIdAndDefault = rewrite_receivingRepository.findByUserIdAndDefault(UserId);
            if (!CheckUtils.checkObj(byUserIdAndDefault))
                return Result.fail("当前用户无默认收货地址");
            return Result.suc("获取成功", byUserIdAndDefault);
        }
    }

    /**
     * 修改默认收获地址
     *
     * @param id
     * @return
     */
//    public Result setDefaultAddress(Long id, Long userId) {
//        //判断当前用户修改的收货地址是否是默认收货地址
//        //是的话就不设置成默认收货地址
//        //不是则设置成默认收货地址,并且将默认收货地址修改成非默认
//        if (!CheckUtils.checkLongByZero(id))
//            return Result.fail("地址数据错误");
//        else {
//            Optional<Receiving> byId = rewrite_receivingRepository.findById(id);
//            Receiving receiving = byId.get();
//            if (receiving.isIsdefault())
//                receiving.setIsdefault(false);
//            else {
//                Receiving byUserIdAndDefault = rewrite_receivingRepository.findByUserIdAndDefault(Long.valueOf(receiving.getUserid()));
//                byUserIdAndDefault.setIsdefault(false);
//                Receiving save = rewrite_receivingRepository.saveAndFlush(byUserIdAndDefault);
//                if (!CheckUtils.checkObj(save))
//                    return Result.fail("网络繁忙请稍后重试");
//                receiving.setIsdefault(true);
//            }
//            Receiving result = rewrite_receivingRepository.saveAndFlush(receiving);
//            if (!CheckUtils.checkObj(result))
//                return Result.fail("网络繁忙请稍后重试");
//            return Result.suc("设置成功");
//        }
//    }

    /**
     * @param userid
     * @param id
     * @return
     */
    public Result getOneUserAddress(Long userid, Long id) {
        if (!CheckUtils.checkLongByZero(userid))
            return Result.fail("用户表示错误");
        else if (!CheckUtils.checkLongByZero(id))
            return Result.fail("收货信息表示错误");
        else {
            Receiving byUserIdAndId = rewrite_receivingRepository.findByUserIdAndId(userid, id);
            if (!CheckUtils.checkObj(byUserIdAndId))
                return Result.fail("网络繁忙");
            return Result.suc("获取成功", byUserIdAndId);
        }
    }
}
